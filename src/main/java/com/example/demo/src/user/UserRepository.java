package com.example.demo.src.user;

import com.example.demo.src.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.demo.common.entity.BaseEntity.State;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndState(Long id, State state);
    Optional<User> findByUsernameAndState(String username, State state);
    Optional<User> findByUsernameAndPhoneNumberAndState(String username, String phoneNumber, State state);
    Optional<User> findByPhoneNumberAndState(String phoneNumber, State state);
    List<User> findAllByState(State state);
    @Query("update User u set u.subscriptionAgreed = false where u.subscriptionAgreedAt <= :expirationDate")
    void updateSubscriptionStatusForExpiredSubscriptions(LocalDateTime expirationDate);

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);

}
