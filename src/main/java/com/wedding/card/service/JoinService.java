package com.wedding.card.service;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.entity.Member;
import com.wedding.card.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public JoinService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public void joinProcess(MemberDTO memberDTO){

        String username = memberDTO.getUsername();
        String password = passwordEncoder.encode(memberDTO.getMemberPwd());
        int memberNo = (int) (Math.random()* 8999)+1000;
        String memberName = memberDTO.getMemberName();
        String memberAddress = memberDTO.getMemberAddress();
        String phoneNum = memberDTO.getPhoneNum();
        String email = memberDTO.getEmail();
        Date birth = memberDTO.getBirth();

        Boolean isExist = memberRepository.existsByUsername(username);

        if (isExist) {

            return ;
        }

        Member member = new Member();
        member.setMemberNo(memberNo);
        member.setUsername(username);
        member.setMemberPwd(password);
        member.setMemberName(memberName);
        member.setMemberAddress(memberAddress);
        member.setPhoneNum(phoneNum);
        member.setEmail(email);
        member.setBirth(birth);
        member.setRole("ROLE_ADMIN");

        memberRepository.save(member);


    }

}
