package com.example.demo.src.admin_user;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.model.GetCondUserRes;
import com.example.demo.src.model.UserSearchCondition;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.Constant.CREATE_AT;
import static com.example.demo.common.code.status.ErrorStatus.NOT_FIND_USER;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminUserService {

    private final AdminUserQueryRepository adminUserQueryRepository;
    private final UserRepository userRepository;

    public Slice<GetCondUserRes> getAdminUsers(UserSearchCondition userSearchCondition, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return adminUserQueryRepository.searchUser(userSearchCondition, pageable);
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
