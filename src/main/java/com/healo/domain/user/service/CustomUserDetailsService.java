package com.healo.domain.user.service;


import com.healo.domain.user.entity.User;
import com.healo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // 로그인 ID로 User 찾기
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Consultant not found"));

//        // UserDetails 객체로 반환
//        return new User(user.getUserId(), user.getUserPwd(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().getValue())));
        return new CustomUserDetails(user);
    }
}
