package com.example.demo.src.admin;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.log.LogRepository;
import com.example.demo.src.admin.model.*;
import com.example.demo.src.admin.model.enums.DomainName;
import com.example.demo.src.board.BoardConverter;
import com.example.demo.src.board.BoardImageRepository;
import com.example.demo.src.board.BoardRepository;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.board.entity.BoardImage;
import com.example.demo.src.board.model.GetBoardImageRes;
import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.comment.CommentConverter;
import com.example.demo.src.comment.CommentRepository;
import com.example.demo.src.comment.model.GetCommentRes;
import com.example.demo.src.mapping.BoardLikesRepository;
import com.example.demo.src.mapping.BoardReportRepository;
import com.example.demo.src.mapping.entity.BoardReport;
import com.example.demo.src.payment.PaymentRepository;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.common.Constant.CREATE_AT;
import static com.example.demo.common.code.status.ErrorStatus.*;
import static com.example.demo.common.entity.BaseEntity.State.ACTIVE;
import static com.example.demo.common.entity.BaseEntity.State.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminService {

    private final AdminQueryRepository adminQueryRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final CommentRepository commentRepository;
    private final BoardImageRepository boardImageRepository;
    private final BoardReportRepository boardReportRepository;
    private final LogRepository logRepository;
    private final PaymentRepository paymentRepository;

    public Page<GetCondUserRes> getAdminUsers(UserSearchCond userSearchCond, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return adminQueryRepository.searchUser(userSearchCond, pageable);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER));
    }

    @Transactional
    public String patchUser(Long userId, State state) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER));
        user.updateState(state);
        return "state 변경 완료";
    }

    public Page<GetCondBoardRes> getAdminBoards(BoardSearchCond boardSearchCond, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return adminQueryRepository.searchBoard(boardSearchCond, pageable);
    }

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

    @Transactional
    public String patchBoard(Long boardId) {
        Board board = boardRepository.findByIdAndState(boardId, ACTIVE)
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
        return "게시글 삭제 완료";
    }

    public Page<GetReportRes> getReports(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return boardReportRepository.findByStateWithFetchJoin(ACTIVE, pageable)
                .map(AdminConverter::toGetReportRes);
    }

    @Transactional
    public String patchReport(Long reportId) {
        BoardReport boardReport = boardReportRepository.findById(reportId)
                .orElseThrow(() -> new BaseException(NOT_FIND_REPORT));
        boardReport.delete();
        return "신고 삭제 완료";
    }

    public Page<?> getLogs(DomainName domainName, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        if(domainName.equals(DomainName.PAYMENT)) {
            return paymentRepository.findByState(ACTIVE, pageable).map(AdminConverter::toGetPaymentRes);
        }
        else {
            String domainControllerName = AdminConverter.toDomainControllerName(domainName);
            return logRepository.findByClassNameAndState(domainControllerName, ACTIVE, pageable).map(AdminConverter::toGetLogRes);
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
