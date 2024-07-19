package com.healo.domain.base.entity;

import lombok.Getter;

public enum Status {
    ACTIVE("활성"),
    INACTIVE("비활성");

    private final String value;
    Status(String state) {
        this.value = state;
    }
    public String getValue(){
        return value;
    }
}
