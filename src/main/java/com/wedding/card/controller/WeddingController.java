package com.wedding.card.controller;

import com.wedding.card.dto.CardDTO;
import com.wedding.card.entity.Card;
import com.wedding.card.service.CardService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping
@Log4j2
@RequiredArgsConstructor
public class WeddingController {

    private final CardService cardService;

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("cardList", cardService.mainList());
        return "/product/mainPage";
    }

    //상품 등록 페이지
    @GetMapping("/productReg")
    public String productReg(Model model) {
        model.addAttribute("cardDTO", new CardDTO());
        return "/product/productReg";
    }

    //상품 등록 post
    @PostMapping("/productReg")
    public String productRegPost(@ModelAttribute CardDTO cardDTO, @RequestPart("picName") MultipartFile picName) throws IOException {
        if (!picName.isEmpty()) {
            cardService.regProd(cardDTO, picName);
        } else{
            cardService.regProd(cardDTO);
        }
        return "redirect:/main";
    }

    //상품 페이지
    @GetMapping("/productDetail")
    public String productDetail(Model model, @RequestParam("id") Long id) {
        model.addAttribute("product",cardService.prodDetail(id));
        return "/product/productDetail";
    }

    @PostMapping("/prodDelete/{id}")
    public String productDelete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) throws FileNotFoundException {
        cardService.deleteProd(id);
//        redirectAttributes.addAttribute("message", id + "번 게시글이 삭제되었습니다.");
        return "redirect:/main";
    }

    @GetMapping("/productUpdate/{id}")
    public String productUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", cardService.prodDetail(id));

        return "/product/productUpdate";
    }


    //update 아직 안됨 , update 메서드 만들어야 함
    @PostMapping("/productUpdate/{id}")
    public String productUpdatePost(@PathVariable("id")Long id, @ModelAttribute CardDTO cardDTO, @RequestParam(value = "picName", required = false) MultipartFile picName) throws IOException {
            cardService.updateProd(cardDTO, picName, id);


        return "redirect:/main";
    }


//    @PostMapping("/productUpdate/{id}")
//    public String productUpdatePost(@PathVariable("id")Long id, @ModelAttribute CardDTO cardDTO ,@RequestParam("picName") MultipartFile picName) throws IOException {
//        if (!picName.isEmpty()) {
//            cardService.regProd(cardDTO, picName);
//        }else {
//            cardService.regProd(cardDTO);
//        }
//
//        return "redirect:/main";
//    }

}
