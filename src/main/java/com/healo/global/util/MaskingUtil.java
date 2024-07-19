package com.healo.global.util;

public class MaskingUtil {
    public static String maskId(String userId) {
        if (userId == null || userId.length() < 4) {
            return userId;
        }
        return userId.substring(0, 2) + "*".repeat(userId.length() - 4) + userId.substring(userId.length() - 2);
    }

    public static String maskName(String name) {
        if (name == null || name.length() < 1) {
            return name;
        }
        return name.charAt(0) + "*".repeat(name.length() - 1);
    }

    public static String maskPhone(String phone) {
        if (phone == null || phone.length() < 7) {
            return phone;
        }
        return phone.substring(0, 3) + "*".repeat(phone.length() - 7) + phone.substring(phone.length() - 4);
    }
}
