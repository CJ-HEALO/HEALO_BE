package com.healo.global.config.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // User
    USER_NOT_FOUND("E100", "존재하지 않는 회원입니다."),
    USER_LOGIN_INCORRECT("E101", "아이디 또는 비밀번호가 다릅니다."),
    USER_REGISTER_VALIDATION_FAIL("E102", "비밀번호 또는 이메일 형식이 올바르지 않습니다."),
    USER_NOT_ADMIN("E103","관리자만 접근이 가능합니다");

    private final String code;
    private final String message;
}
