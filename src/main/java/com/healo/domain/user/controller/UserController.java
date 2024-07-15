package com.healo.domain.user.controller;

import com.healo.domain.user.dto.request.SignUpReqDto;
import com.healo.domain.user.entity.User;
import com.healo.domain.user.service.UserService;
import com.healo.global.config.error.exception.CustomException;
import com.healo.global.config.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.healo.global.config.error.ErrorCode.USER_REGISTER_VALIDATION_FAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping
    public ApiResponse<Object> createUser(@RequestBody SignUpReqDto user) {
        return userService.register(user);
    }
}
