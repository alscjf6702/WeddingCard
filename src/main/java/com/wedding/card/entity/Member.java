package com.wedding.card.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String MemberNo;

    @Column(nullable = false)
    private String MemberId;

    @Column(nullable = false)
    private String MemberName;

    @Column(nullable = false)
    private String MemberPwd;

    @Column(nullable = false)
    private String MemberAddress;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String email;

    private Date birth;

    @Column(updatable = false)
    private Date regDate;

    @ColumnDefault("1") //1=활동 / 0=휴면
    private int stopMember;




}

