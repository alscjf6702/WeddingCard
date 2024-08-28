package com.wedding.card.controller;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.entity.Member;
import com.wedding.card.service.InfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/member/update")
    public String update(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = infoService.findByUsername(username);
        model.addAttribute("member", member);

        return "/member/memberUpdate";
    }

    @PostMapping("/member/update")
    public String doUpdate(MemberDTO memberDTO) {

        infoService.updateMember(memberDTO);

        System.out.println(memberDTO.getEmail());

        return "redirect:/member/info";
    }

    @PostMapping("/member/delete")
    public String delete(MemberDTO dto, HttpServletResponse response, HttpServletRequest request) {

        infoService.DeleteMember(dto, request, response);
        return "redirect:/main";

    }


    @GetMapping("/member/purchasedProduct")
    public String purchasedProduct(Model model){

         if (infoService.purchasedProduct() != null) {
            model.addAttribute("purchasedProduct", infoService.purchasedProduct());
            return "/member/purchasedProduct";
        }else{
             model.addAttribute("nothingPurchased", "구매 내역이 없습니다.");
             return "/member/purchasedProduct";
         }

    }
}
