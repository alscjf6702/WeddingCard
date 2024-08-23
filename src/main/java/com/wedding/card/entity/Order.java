package com.wedding.card.entity;

import com.wedding.card.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String merchantUid; // 주문 고유 번호

    @Column(nullable = false)
    private String impUid; // 결제 고유 식별자

    @Column(nullable = false)
    private String productName; // 상품 이름

    @Column(nullable = false)
    private int amount; // 결제 금액

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 주문한 회원 정보와의 관계

    @Column(nullable = false)
    private String paymentStatus; // 결제 상태

    @Column(nullable = false)
    private LocalDateTime orderDate; // 주문 일자

    public OrderDTO toDTO(Order order){ //entity to dto
        return OrderDTO.builder()
                .id(order.getId())
                .merchantUid(order.getMerchantUid())
                .memberId(order.getMember().getId())
                .productName(order.getProductName())
                .amount(order.getAmount())
                .orderDate(LocalDateTime.now())
                .paymentStatus(order.getPaymentStatus())
                .build();
    }

}
