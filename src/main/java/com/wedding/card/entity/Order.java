package com.wedding.card.entity;

import com.wedding.card.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@DynamicUpdate  //수정 시 null값인 컬럼은 쿼리문 전달할 때 제외
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
    @JoinColumn(name = "member_id", nullable = true)
    private Member member; // 주문한 회원 정보와의 관계

    @Column(nullable = false)
    private String paymentStatus; // 결제 상태

    @Column(nullable = false)
    private LocalDateTime orderDate; // 주문 일자

    @Column(nullable = true)
    private String productCode; //상품 코드

    @PrePersist
    protected void onCreate(){
        this.orderDate = LocalDateTime.now();
    }


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
