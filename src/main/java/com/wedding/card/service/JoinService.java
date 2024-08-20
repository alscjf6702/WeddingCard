package com.wedding.card.service;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.entity.Member;
import com.wedding.card.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public JoinService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //memberNo 중복방지
    private int memberNoExist() {
        int memberNo = (int) (Math.random() * 8999) + 1000;
        Boolean memberNoIsExist = memberRepository.existsByMemberNo(memberNo);
        if(memberNoIsExist){
            memberNo = (int) (Math.random()*89999) + 10000;
            return memberNo;
        }return memberNo;
    }


    public void joinProcess(MemberDTO memberDTO){


        String username = memberDTO.getUsername();
        String password = memberDTO.getMemberPwd();
        int memberNo = memberNoExist();
        String memberName = memberDTO.getMemberName();
        String memberAddress = memberDTO.getMemberAddress();
        String phoneNum = memberDTO.getPhoneNum();
        String email = memberDTO.getEmail();
        Date birth = memberDTO.getBirth();

        Boolean isExist = memberRepository.existsByUsername(username);

        if (isExist) {
            throw new IllegalArgumentException("중복된 ID 입니다.");
        }
        if (username.isEmpty()) {
            throw new IllegalArgumentException("ID를 입력해주세요.");
        }
        if (memberDTO.getMemberPwd().isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

        if (memberName.isEmpty()) {
            throw new IllegalArgumentException("성함을 입력해주세요.");
        }
        if (phoneNum.isEmpty()) {
            throw new IllegalArgumentException("휴대폰 번호를 입력해주세요.");
        }

        Member member = new Member();
        member.setMemberNo(memberNo);
        member.setUsername(username);
        member.setMemberPwd(passwordEncoder.encode(password));
        member.setMemberName(memberName);
        member.setMemberAddress(memberAddress);
        member.setPhoneNum(phoneNum);
        member.setEmail(email);
        member.setBirth(birth);
        member.setRole("ROLE_USER");


        memberRepository.save(member);
    }



}
