package com.wedding.card.dto;

import com.wedding.card.entity.Card;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Transactional
public class CardDTO {

    private String productName; //상품명

    private String productCode; //상품 코드

    private int price;  //상품 가격

    private int discount;   //할인률(default0)

    private int quantity;   //상품 수량(default0)

    private MultipartFile picName; //파일 이름

    private String picPath; //파일 경로

    public Card toEntity(){
        return Card.builder()
                .productName(productName)
                .productCode(productCode)
                .price(price)
                .discount(discount)
                .quantity(quantity)
                .picName(picName.getOriginalFilename())
                .picPath(picPath)
                .build();
    }

//    public Card picToEntity(MultipartFile picName, String picPath) {
//        return Card.builder()
//                .picName(picName.getOriginalFilename())
//                .picPath(picPath)
//                .build();
//    }

}
