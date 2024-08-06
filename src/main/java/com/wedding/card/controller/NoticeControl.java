package com.wedding.card.controller;

import com.wedding.card.dto.NoticeDTO;
import com.wedding.card.entity.Notice;
import com.wedding.card.service.NoticeService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeControl {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public String noticeList(Model model, Pageable pageable) {

        Page<Notice> page = noticeService.noticeList(pageable);
        model.addAttribute("list", page.getContent());
        model.addAttribute("page", page);

        return "/notice/noticeList";
    }

    @GetMapping("/regNotice")
    public String regNotice() {

        return "/notice/regNotice";
    }

    @PostMapping("/regNotice")
    public String regNoticePost(NoticeDTO dto) {
        noticeService.writeNotice(dto);
        return "redirect:/notice/list";
    }


    @GetMapping("/detail")
    public String noticeDetail(@RequestParam("id") Long id,Model model){
        model.addAttribute("detail", noticeService.findDetail(id));
        return "/notice/noticeDetail";
    }
}
