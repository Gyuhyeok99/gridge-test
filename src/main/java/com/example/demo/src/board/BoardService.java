package com.example.demo.src.board;


import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.board.entity.BoardImage;
import com.example.demo.src.board.model.*;
import com.example.demo.src.comment.CommentConverter;
import com.example.demo.src.comment.CommentRepository;
import com.example.demo.src.comment.model.GetCommentRes;
import com.example.demo.src.mapping.BoardLikesRepository;
import com.example.demo.src.mapping.entity.BoardLikes;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.demo.common.Constant.CREATE_AT;
import static com.example.demo.common.code.status.ErrorStatus.NOT_FIND_BOARD;
import static com.example.demo.common.entity.BaseEntity.State.ACTIVE;
import static com.example.demo.common.entity.BaseEntity.State.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final CommentRepository commentRepository;

    public GetBoardRes getBoard(Long boardId) {
        Board board = boardRepository.findByIdAndState(boardId, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_BOARD));
        Long likesCount = boardLikesRepository.countByBoardId(board.getId());
        Long commentsCount = commentRepository.countByBoardId(board.getId());
        List<GetCommentRes> commentsRes = getGetCommentRes(board);
        List<BoardImage> imageUrls = getBoardImages(board);
        List<GetBoardImageRes> getBoardImageRes = getGetBoardImageRes(imageUrls);
        return BoardConverter.toGetBoardRes(board, getBoardImageRes, likesCount, commentsCount, commentsRes);
    }



    public Slice<GetBoardRes> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        Slice<Board> boards = boardRepository.findByStateWithUser(ACTIVE, pageable);

        List<GetBoardRes> boardResList = boards.getContent().stream().map(board -> {
            Long likesCount = boardLikesRepository.countByBoardId(board.getId());
            Long commentsCount = commentRepository.countByBoardId(board.getId());

            List<GetCommentRes> commentsRes = null;
            if (commentsCount <= 2) {
                commentsRes = commentRepository.findByBoardIdAndState(board.getId(), ACTIVE).stream()
                        .map(CommentConverter::toGetCommentRes)
                        .toList();
            }
            List<BoardImage> imageUrls = getBoardImages(board);
            List<GetBoardImageRes> getBoardImageRes = getGetBoardImageRes(imageUrls);
            return BoardConverter.toGetBoardRes(board, getBoardImageRes, likesCount, commentsCount, commentsRes);
        }).toList();
        return new SliceImpl<>(boardResList, pageable, boards.hasNext());
    }



    @Transactional
    public PostBoardRes createdBoard(User user, PostBoardReq postBoardReq) {
        Board board = BoardConverter.toBoard(postBoardReq, user);
        boardRepository.save(board);
        createdBoardImage(postBoardReq, board);
        return BoardConverter.toPostBoardRes(board.getId());
    }

    @Transactional
    public PatchBoardRes editedBoard(User user, Long boardId, PatchBoardReq patchBoardReq) {
        Board board = boardRepository.findByIdAndUserAndState(boardId, user, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_BOARD));
        board.setContent(patchBoardReq.getContent()); // 내용 갱신
        // 사진 리스트 처리
        List<PatchBoardImageReq> newImages = patchBoardReq.getBoardImageReqs();
        List<BoardImage> existingImages = boardImageRepository.findByBoardIdAndState(boardId, ACTIVE);

        // 새로운 이미지 추가 또는 기존 이미지 갱신
        for (PatchBoardImageReq newImage : newImages) {
            Optional<BoardImage> existingImage = existingImages.stream()
                    .filter(img -> img.getImageUrl().equals(newImage.getImageUrl()))
                    .findFirst();
            if (existingImage.isPresent()) {
                // 이미지 순서 갱신 등
                existingImage.get().setImageOrder(newImage.getImageOrder());
            } else {
                // 새로운 이미지 추가
                boardImageRepository.save(BoardConverter.toBoardImage(newImage, board));
            }
        }

        // 기존에는 있지만 PatchBoardImageReq에 없는 이미지 삭제
        List<String> newImageUrls = newImages.stream().map(PatchBoardImageReq::getImageUrl).toList();
        existingImages.forEach(existingImage -> {
            if (!newImageUrls.contains(existingImage.getImageUrl())) {
                existingImage.setState(INACTIVE);
            }
        });
        return BoardConverter.toPatchBoardRes(board.getId());
    }

    @Transactional
    public String deletedBoard(User user, Long boardId) {
        Board board = boardRepository.findByIdAndUserAndState(boardId, user, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_BOARD));
        boardImageRepository.findByBoardIdAndState(board.getId(), ACTIVE).stream().map(boardImage -> {
            boardImage.setState(INACTIVE);
            return boardImage;
        }).forEach(boardImageRepository::save);
        commentRepository.findByBoardIdAndState(board.getId(), ACTIVE).stream().map(comment -> {
            comment.setState(INACTIVE);
            return comment;
        }).forEach(commentRepository::save);
        board.setState(INACTIVE);
        return "게시글 삭제 성공";
    }

    @Transactional
    public PostLikesRes toggleLike(Long boardId, User user) {
        Board board = boardRepository.findByIdAndState(boardId, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_BOARD));
        // 좋아요 상태 확인
        Optional<BoardLikes> like = boardLikesRepository.findByBoardIdAndUser(boardId, user);
        if (like.isPresent()) {
            boardLikesRepository.delete(like.get());
        } else {
            boardLikesRepository.save(BoardConverter.toBoardLikes(board, user));
        }
        return BoardConverter.toPostLikesRes(board.getId(), boardLikesRepository.countByBoardId(board.getId()));
    }

    private void createdBoardImage(PostBoardReq postBoardReq, Board board) {
        if (postBoardReq.getBoardImageReqs() != null) {
            postBoardReq.getBoardImageReqs().forEach(imageReq -> {
                BoardImage boardImage = BoardConverter.toBoardImage(imageReq, board);
                boardImageRepository.save(boardImage);
            });
        }
    }

    private List<BoardImage> getBoardImages(Board board) {
        return boardImageRepository.findByBoardIdAndState(board.getId(), ACTIVE).stream().toList();
    }

    private List<GetCommentRes> getGetCommentRes(Board board) {
        return commentRepository.findByBoardIdAndState(board.getId(), ACTIVE).stream()
                .map(CommentConverter::toGetCommentRes)
                .toList();
    }

    private static List<GetBoardImageRes> getGetBoardImageRes(List<BoardImage> imageUrls) {
        return imageUrls.stream().map(BoardConverter::toGetBoardImageRes).toList();
    }
}
