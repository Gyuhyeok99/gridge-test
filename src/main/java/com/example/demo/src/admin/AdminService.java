package com.example.demo.src.admin;


import com.example.demo.src.admin.model.GetCondUserRes;
import com.example.demo.src.admin.model.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.Constant.CREATE_AT;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminService {

    private final AdminQueryRepository adminQueryRepository;

    public Slice<GetCondUserRes> getAdminUsers(UserSearchCondition userSearchCondition, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return adminQueryRepository.searchUser(userSearchCondition, pageable);
    }

}
