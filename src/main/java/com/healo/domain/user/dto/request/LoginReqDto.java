package com.healo.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class LoginReqDto {
    @NotEmpty(message = "아이디를 입력하세요.")
    private String userId;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String userPwd;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, userPwd);
    }
}
