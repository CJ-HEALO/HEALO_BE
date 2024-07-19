package com.healo.domain.user.dto.response;

import com.healo.global.util.MaskingUtil;
import lombok.Data;

@Data
public class UserListResDto {
    private String userId;
    private String name;
    private String phoneNumber;

    public UserListResDto(String id, String name, String phone) {
        this.userId = MaskingUtil.maskId(id);
        this.name = MaskingUtil.maskName(name);
        this.phoneNumber = MaskingUtil.maskPhone(phone);
    }

}
