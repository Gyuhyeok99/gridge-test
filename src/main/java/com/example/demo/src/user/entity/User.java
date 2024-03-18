package com.example.demo.src.user.entity;

import com.example.demo.common.Constant.SocialLoginType;
import com.example.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "users") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class User extends BaseEntity implements UserDetails {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "user_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 10)
    private LocalDate birth;

    @Column(nullable = false)
    private boolean isOAuth;

    @Enumerated(EnumType.STRING)
    private SocialLoginType socialLoginType;


    private Boolean termsAgreed;

    private LocalDate termsAgreedDate;

    private LocalDateTime lastLoginAt;

    private Boolean subscriptionAgreed = false;
    private LocalDateTime subscriptionAgreedAt;
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;


    public void updateName(String name) {
        this.name = name;
    }

    public void deleteUser() {
        this.state = State.INACTIVE;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //setter
    public void setPassword(String password) {
        this.password = password;
    }


    public void updateLastLoginAt() {
        this.lastLoginAt = LocalDateTime.now();
    }

    public void updateState(State staste) {
        this.setState(staste);
    }

    public void updateTermsAgreedStatus() {
        if (termsAgreedDate.plusYears(1).isBefore(LocalDate.now())) {
            this.termsAgreed = false;
        }
    }

    public void updateSubscriptionAgreed(boolean subscriptionAgreed) {
        this.subscriptionAgreed = subscriptionAgreed;
        this.subscriptionAgreedAt = LocalDateTime.now();
    }
}
