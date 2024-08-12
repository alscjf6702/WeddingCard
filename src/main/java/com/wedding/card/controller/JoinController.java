package com.wedding.card.controller;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.service.JoinService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Log4j2
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String joinMember() {

        return "/member/memberJoin";
    }


    @PostMapping("/join")
    public String joinProcess(@ModelAttribute MemberDTO memberDTO, Model model) {
        try {
            joinService.joinProcess(memberDTO);
            return "redirect:/main";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "/member/memberJoin";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "/member/login";
    }
}
