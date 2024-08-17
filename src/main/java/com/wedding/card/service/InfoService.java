package com.wedding.card.service;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.entity.Member;
import com.wedding.card.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final InfoRepository infoRepository;


    public Member findByUsername(String username){
        return infoRepository.findByUsername(username);
    }

    public void updateMember(MemberDTO dto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = infoRepository.findByUsername(username);

        member.setMemberAddress(dto.getMemberAddress());
        member.setEmail(dto.getEmail());

        infoRepository.save(member);
    }
}
