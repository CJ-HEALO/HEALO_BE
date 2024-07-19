package com.healo.domain.user.entity;

public enum RoleType {
    USER("유저"),
    ADMIN("관리자");
    private final String value;
    RoleType(String value) {this.value = value;}
    public String getValue() {return value;}

}
