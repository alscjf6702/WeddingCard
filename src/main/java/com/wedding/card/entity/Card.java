package com.wedding.card.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@DynamicInsert  //삽입 시 null값인 컬럼은 쿼리문 전달할 때 제외
@DynamicUpdate  //수정 시 null값인 컬럼은 쿼리문 전달할 때 제외
@ToString
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName; //상품명

    @Column(nullable = false)
    private String productCode; //상품 코드

    @Column(nullable = false)
    private int price;  //상품 가격

    @Column(nullable = true)
    @ColumnDefault("0")     //column의 기본 값 설정
    private int discount;   //할인률

    @Column(nullable = true)
    @ColumnDefault("0")
    private int quantity;   //상품 수량

    @Column(nullable = true)
    private String picName; //파일 이름

    @Column(nullable = true)
    private String picPath; //파일 경로



}
