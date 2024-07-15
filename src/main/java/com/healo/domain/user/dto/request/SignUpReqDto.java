package com.healo.domain.user.dto.request;

import com.healo.domain.user.entity.RoleType;
import com.healo.domain.user.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpReqDto {

    @NotEmpty(message = "이름을 입력하세요.")
    private String userName; // 유저이름

    @NotEmpty(message = "휴대폰번호를 입력하세요.")
    private String phoneNumber; //유저 핸드폰 번호

    @NotEmpty(message = "생년월일을 입력하세요.")
    private String birthday;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{4,12}$",
            message = "아이디는 4~12자 영문 대 소문자와 숫자를 사용하세요.")
    @NotEmpty(message = "ID를 입력하세요.")
    private String userId; // 유저 아이디

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[a-zA-Z\\d~!@#$%^&*()+|=]{8,}$",
            message = "비밀번호는 8자 이상 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @NotEmpty(message = "패스워드를 입력하세요.")
    private String password; // 유저 패스워드

    @NotNull(message = "필수 이용약관 동의여부를 입력하세요.")
    private int serviceCheck; // 서비스 이용동의

    @NotNull(message = "개인정보 수집 동의여부를 입력하세요.")
    private int personalCheck; // 개인정보 이용동의

    public User toEntity() {
        return User.builder()
                .name(userName)
                .phoneNumber(phoneNumber)
                .birthday(birthday)
                .userId(userId)
                .password(password)
                .serviceCheck(serviceCheck)
                .personalCheck(personalCheck)
                .role(RoleType.USER).build();
    }

}
