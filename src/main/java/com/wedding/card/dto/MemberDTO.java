package com.wedding.card.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private int MemberNo;

    private String username;

    private String memberName;

    private String memberPwd;

    private String memberAddress;

    private String phoneNum;

    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private Date regDate;

    private int stopMember;





}

