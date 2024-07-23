package com.healo.domain.user.entity;

import com.healo.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "user")
@Table(name = "user")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@ToString
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    @Builder.Default
    private RoleType role = RoleType.USER;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String birthday;

    @Column(nullable = false)
    private Gender gender;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column
    private String userThreshold;

    @Column
    private String userWork;

    @Column(name = "service_check", nullable = false)
    @ColumnDefault("0")
    private int serviceCheck;

    @Column(name = "personal_check", nullable = false)
    @ColumnDefault("0")
    private int personalCheck;


    
}
