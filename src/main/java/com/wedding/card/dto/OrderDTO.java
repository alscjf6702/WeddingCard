package com.wedding.card.dto;


import com.wedding.card.entity.Member;
import com.wedding.card.entity.Order;
import com.wedding.card.repository.MemberRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private String merchantUid; // 주문 고유 번호
    private Long memberId; // 주문한 회원의 ID
    private String productName; // 주문한 상품명
    private int amount; // 결제 금액
    private LocalDateTime orderDate; // 주문 날짜
    private String paymentStatus; // 결제 상태  (paid, unpaid)
    private String impUid;
    private String productCode;



}