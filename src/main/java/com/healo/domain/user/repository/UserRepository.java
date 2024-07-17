package com.healo.domain.user.repository;

import com.healo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 첫 사용자만 ADMIN ROLE 을 부여하기 위한 카운트
    long count();

    Optional<User> findByUserId(String userId);
}
