package com.wedding.card.controller;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.service.JoinService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@Log4j2
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String joinMember(){

        return "/member/memberJoin";
    }


    @PostMapping("/join")
    public String joinProcess(MemberDTO memberDTO){
        log.info(memberDTO.getMemberPwd());
        log.info(memberDTO.getMemberName());
        joinService.joinProcess(memberDTO);

        return "redirect:/main";
    }


}
