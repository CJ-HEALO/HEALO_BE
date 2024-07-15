package com.healo.global.config.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // User
    USER_REGISTER_CHECK_SUCCESS(OK, "회원가입 여부 확인 성공"),
    USER_REGISTER_CHECK_EMAIL_SUCCESS(OK, "이메일 중복 확인 성공"),
    USER_REGISTER_CHECK_ID_SUCCESS(OK, "아이디 중복 확인 성공"),
    USER_REGISTER_SUCCESS(CREATED, "회원가입 성공"),
    USER_LOGIN_SUCCESS(OK, "로그인 성공");




    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusValue() {
        return httpStatus.value();
    }
}
