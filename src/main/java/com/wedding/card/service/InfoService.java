package com.wedding.card.service;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.entity.Member;
import com.wedding.card.repository.InfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    public void DeleteMember(MemberDTO dto, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = infoRepository.findByUsername(username);

        Long id = member.getId();

        infoRepository.deleteById(id);

        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }

}
