
package com.wedding.card.service;

import com.wedding.card.dto.CardDTO;
import com.wedding.card.entity.Card;
import com.wedding.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;


    //main화면 리스트
    public List<Card> mainList(){
        return cardRepository.findAll();
    }

    public Optional<Card> prodDetail(Long id){
        return cardRepository.findById(id);
    }

    public void deleteProd(Long id) throws FileNotFoundException {

        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            String picPath = card.getPicPath();

            // 파일 삭제
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/static" + picPath);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("파일 삭제 완료");
                } else {
                    System.out.println("파일 삭제 실패");
                }
            }

            // 엔티티 삭제
            cardRepository.delete(card);
        } else {
            throw new FileNotFoundException( id+ " 번 게시글이 존재하지 않습니다.");
        }

    }

    //상품 등록
    public Card regProd(CardDTO cardDTO, MultipartFile file) throws IOException {

        if (!file.isEmpty() && file.getResource().exists()) {
            UUID uuid = UUID.randomUUID();
            String projectPath = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\uploads";
            String fileName = uuid + "_"+ file.getOriginalFilename();
            String detailPath = System.getProperty("user.dir") + "/src/main/resources/static/uploads/" + fileName;
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);

            cardDTO.setPicPath("/uploads/"+ fileName);

        }else {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploads";
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);

            cardDTO.setPicPath("/uploads/" + fileName);

        }
        Card card = cardDTO.toEntity();
        return cardRepository.save(card);
    }

    public Card regProd(CardDTO cardDTO){

        Card card = cardDTO.toEntity();

        return cardRepository.save(card);
    }


    public void updateProd(CardDTO cardDTO, MultipartFile picName, Long id) throws IOException {
        String originName = picName.getOriginalFilename();
        if (!picName.isEmpty()) {

            UUID uuid = UUID.randomUUID();

            String uuidName = uuid + "_" + originName;
            String picPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploads";
            File file = new File(picPath, uuidName);
            picName.transferTo(file);
            Card card = Card.builder()
                    .id(id)
                    .productName(cardDTO.getProductName())
                    .price(cardDTO.getPrice())
                    .productCode(cardDTO.getProductCode())
                    .quantity(cardDTO.getQuantity())
                    .discount(cardDTO.getDiscount())
                    .picPath("/uploads/"+uuidName)
                    .picName(picName.getOriginalFilename())
                    .build();


            cardRepository.save(card);
        }else{

            Optional<Card> cardOptional = cardRepository.findById(id);
            if (cardOptional.isPresent()) {
                Card card = cardOptional.get();
                String savedPath = card.getPicPath();

                System.out.println("저장된 경로"+savedPath);
                System.out.println("새로운 경로"+cardDTO.getPicPath());
                // 파일 삭제
                File file = new File(System.getProperty("user.dir") + "/src/main/resources/static" + savedPath);
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("파일 삭제 완료");
                    } else {
                        System.out.println("파일 삭제 실패");
                    }
                }
            card = Card.builder()
                    .id(id)
                    .productName(cardDTO.getProductName())
                    .price(cardDTO.getPrice())
                    .productCode(cardDTO.getProductCode())
                    .quantity(cardDTO.getQuantity())
                    .discount(cardDTO.getDiscount())
                    .picName(card.getPicName())
                    .picPath(savedPath)
                    .build();

            cardRepository.save(card);
            }
        }

    }


}
