package com.wedding.card.controller;

import com.wedding.card.dto.NoticeDTO;
import com.wedding.card.entity.Notice;
import com.wedding.card.service.NoticeService;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
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
    public String regNotice(@RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "size", required = false) Integer size,
                            Model model) {
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "/notice/regNotice";
    }

    @PostMapping("/regNotice")
    public String regNoticePost(NoticeDTO dto) {
        noticeService.writeNotice(dto);

        return "redirect:/notice/list";
    }


    @GetMapping("/detail")
    public String noticeDetail(@RequestParam("id") Long id, @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "size", required = false) Integer size, Model model) {

        noticeService.plusReadCount(id);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("detail", noticeService.findDetail(id));
        return "/notice/noticeDetail";
    }

    @PostMapping("/delete")
    public String deleteNotice(@RequestParam("id") Long id) {
        noticeService.deleteNotice(id);
        return "redirect:/notice/list";
    }

    @GetMapping("/updateNotice")
    public String updateNotice(@RequestParam("id") Long id, Model model) {
        log.info("ID값은 "+id);
        model.addAttribute("detail", noticeService.findDetail(id));

        return "/notice/updateNotice";
    }

    @PostMapping("/updateNotice")
    public String updateNoticePost(NoticeDTO dto){
        log.info(dto);
        noticeService.updateNotice(dto);

        return "redirect:/notice/list";
    }
}
