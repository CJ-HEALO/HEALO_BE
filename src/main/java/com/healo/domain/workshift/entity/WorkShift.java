package com.healo.domain.workshift.entity;

import com.healo.domain.base.entity.BaseEntity;
import com.healo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "work_shift")
@Table(name = "work_shift")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Data
public class WorkShift extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftId;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    // 근무시작시간
    @Column(nullable = false)
    private LocalDateTime startTime;

    // 근무종료시간
    @Column
    private LocalDateTime endTime;

    // 근무장소
    @Column(nullable = false)
    private String location;
}
