package com.example.demo.src.admin;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.admin.model.BoardSearchCond;
import com.example.demo.src.admin.model.GetCondBoardRes;
import com.example.demo.src.admin.model.GetCondUserRes;
import com.example.demo.src.admin.model.UserSearchCond;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.Constant.CREATE_AT;
import static com.example.demo.common.code.status.ErrorStatus.NOT_FIND_USER;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminService {

    private final AdminQueryRepository adminQueryRepository;
    private final UserRepository userRepository;

    public Page<GetCondUserRes> getAdminUsers(UserSearchCond userSearchCond, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return adminQueryRepository.searchUser(userSearchCond, pageable);
    }

    public Page<GetCondBoardRes> getAdminBoards(BoardSearchCond boardSearchCond, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return adminQueryRepository.searchBoard(boardSearchCond, pageable);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER));
    }

    @Transactional
    public String patchUser(Long userId, State staste) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(NOT_FIND_USER));
        user.updateState(staste);
        return "state 변경 완료";
    }


}
