package com.healo.domain.user.service;

/*
토큰으로 사용될 유저 정보와 권한을 저장
 */

import com.healo.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 계정이 가지고 있는 권한 목록 리턴
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {  // 계정이 만료되었는지 리턴 true는 만료되지 않음을 나타냄
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   // 계정이 잠겼는지 리턴 true는 잠기지 않음을 나타냄
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 비밀번호가 만료되었는지 리턴 true는 만료되지 않음을 나타냄
        return true;
    }

    @Override
    public boolean isEnabled() {    // 계정 활성화 여부를 리턴 true는 활성화됨을 나타냄
        return true;
    }

}