package com.healo.domain.user.entity;

public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private final String value;
    Gender(String value) {this.value = value;}
    public String getValue() {return value;}
}
