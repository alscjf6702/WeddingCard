package com.wedding.card.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int memberNo;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberPwd;

    @Column(nullable = false)
    private String memberAddress;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String email;

    private String role;

    private String orderState;

    @Column(nullable = false)
    private Date birth;

    private Date regDate;

    @ColumnDefault("0") //0=활동 / 1=휴면
    private int stopMember;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        this.regDate = new Timestamp(System.currentTimeMillis());
    }



}

