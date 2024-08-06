package com.wedding.card.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private String MemberNo;

    private String MemberId;

    private String MemberName;

    private String MemberPwd;

    private String MemberAddress;

    private String phoneNum;

    private String email;

    private Date birth;

    private Date regDate;

    private int stopMember;





}

