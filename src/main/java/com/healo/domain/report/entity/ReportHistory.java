package com.healo.domain.report.entity;

import com.healo.domain.base.entity.BaseEntity;
import com.healo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "report_history")
@Table(name = "report_history")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Data
public class ReportHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportCode;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    // 신고장소
    @Column(nullable = false)
    private String reportPlace;

    // 신고내용
    @Column(nullable = false)
    private String reportContent;


}
