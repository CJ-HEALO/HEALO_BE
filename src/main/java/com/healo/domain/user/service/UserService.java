package com.healo.domain.user.service;

import com.healo.domain.user.dto.request.SignUpReqDto;
import com.healo.domain.user.repository.UserRepository;
import com.healo.global.config.error.exception.CustomException;
import com.healo.global.config.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.healo.global.config.error.ErrorCode.USER_REGISTER_VALIDATION_FAIL;
import static com.healo.global.config.response.SuccessCode.USER_REGISTER_SUCCESS;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ApiResponse<Object> register(SignUpReqDto signUpReqDto) {
        try {
            log.info(">>>>>>>>>>>>>>>>>>>>> 여기서 안돼??" + signUpReqDto.toString());
            userRepository.save(signUpReqDto.toEntity());
            return ApiResponse.success(USER_REGISTER_SUCCESS);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(USER_REGISTER_VALIDATION_FAIL);
        }
    }
}
