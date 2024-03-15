package com.example.demo.src.scheduler;

import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.common.entity.BaseEntity.State.ACTIVE;

@Component
@RequiredArgsConstructor
@Slf4j
public class TermsAgreedScheduler {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateTermsAgreementStatus() {

        List<User> users = userRepository.findAllByState(ACTIVE);
        for (User user : users) {
            user.updateTermsAgreedStatus();
        }
        userRepository.saveAll(users);
    }
}
