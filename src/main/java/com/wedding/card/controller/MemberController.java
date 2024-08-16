package com.wedding.card.controller;

import com.wedding.card.entity.Member;
import com.wedding.card.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MemberController {

    private final InfoService infoService;

    @GetMapping("/member/info")
    public String info(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 사용자의 이름으로 회원 정보 조회
        Member member = infoService.findByUsername(username);
        model.addAttribute("member", member);

        return "/member/memberInfo";
    }


}
