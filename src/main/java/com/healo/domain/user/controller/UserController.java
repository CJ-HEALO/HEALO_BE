package com.healo.domain.user.controller;

import com.healo.domain.user.dto.request.LoginReqDto;
import com.healo.domain.user.dto.request.SignUpReqDto;
import com.healo.domain.user.entity.RoleType;
import com.healo.domain.user.entity.User;
import com.healo.domain.user.service.UserService;
import com.healo.global.common.type.CurrentUser;
import com.healo.global.config.error.exception.CustomException;
import com.healo.global.config.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.healo.global.config.error.ErrorCode.USER_NOT_ADMIN;
import static com.healo.global.config.error.ErrorCode.USER_REGISTER_VALIDATION_FAIL;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입을 위한 API 입니다.")
    @PostMapping("/register")
    public ApiResponse<?> createUser(@RequestBody SignUpReqDto user) {
        return userService.register(user);
    }

    @Operation(summary = "로그인", description = "로그인을 위한 API 입니다.")
    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginReqDto authRequest) {
        return userService.signIn(authRequest);
    }
    @Operation(summary = "전체회원조회", description = "전체회원조회를 위한 API 입니다.")
    @GetMapping("/userlist")
    public ApiResponse<?> getAllUserList(@CurrentUser User user) {
        if (!user.getRole().equals(RoleType.ADMIN)) {
            throw new CustomException(USER_NOT_ADMIN);
        }
        return userService.allUserList();
    }

}
