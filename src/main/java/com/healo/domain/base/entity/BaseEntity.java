package com.healo.domain.base.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    // Entity 가 생성될 때 자동으로 시간이 설정된다.
    @CreatedDate
    @Column(updatable = false) // 컬럼이 수정되지 않게 막는다.
    private LocalDateTime createdAt;

    @LastModifiedDate // Entity가 수정될 때마다 자동으로 시간이 저장된다.
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ACTIVE;
}
