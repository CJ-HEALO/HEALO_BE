package com.healo.domain.user.service;

import com.healo.domain.user.dto.request.LoginReqDto;
import com.healo.domain.user.dto.request.SignUpReqDto;
import com.healo.domain.user.dto.response.LoginResDto;
import com.healo.domain.user.dto.response.UserListResDto;
import com.healo.domain.user.entity.RoleType;
import com.healo.domain.user.entity.User;
import com.healo.domain.user.repository.UserRepository;
import com.healo.global.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import com.healo.global.config.error.exception.CustomException;
import com.healo.global.config.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.healo.global.config.error.ErrorCode.USER_LOGIN_INCORRECT;
import static com.healo.global.config.error.ErrorCode.USER_REGISTER_VALIDATION_FAIL;
import static com.healo.global.config.response.SuccessCode.USER_LOGIN_SUCCESS;
import static com.healo.global.config.response.SuccessCode.USER_REGISTER_SUCCESS;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public ApiResponse<Object> register(SignUpReqDto signUpReqDto) {
        try {
            String encryptedPassword = passwordEncoder.encode(signUpReqDto.getPassword());
            RoleType role = userRepository.count() == 0 ? RoleType.ADMIN : RoleType.USER;
            userRepository.save(signUpReqDto.toEntity(encryptedPassword, role));
            return ApiResponse.success(USER_REGISTER_SUCCESS);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(USER_REGISTER_VALIDATION_FAIL);
        }
    }
    // 로그인
    public ApiResponse<?> signIn(LoginReqDto loginRequest) {
        if (userRepository.findByUserId(loginRequest.getUserId()).isEmpty()) {
            throw new CustomException(USER_LOGIN_INCORRECT);
        }
        try {
            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
            log.info("[UsernamePasswordAuthenticationToken] : {}", authenticationToken );


            // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            log.info(authentication.toString());

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            LoginResDto loginResDto = jwtTokenProvider.generateToken(authentication);
            log.info("[signIn] 토큰 발급 : {}", loginResDto.toString());

            // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
            //log.info("RT:" + authentication.getName() + " : " + authResponseDto.getRefreshToken() + " : " + TimeUnit.MILLISECONDS);
            // redisTemplate.opsForValue().set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

            return ApiResponse.success(USER_LOGIN_SUCCESS, loginResDto);
        } catch (AuthenticationException e) {
            throw new CustomException(USER_LOGIN_INCORRECT);
        }
    }
    // 전체 유저 조회
    public ApiResponse<?> allUserList() {
        List<User> userList = userRepository.findAll();
        List<UserListResDto> maskedUsers = userList.stream()
                .map(user -> new UserListResDto(user.getUserId(), user.getName(), user.getPhoneNumber()))
                .toList();
        return ApiResponse.success(USER_LOGIN_SUCCESS, userRepository.findAll());
    }
}
