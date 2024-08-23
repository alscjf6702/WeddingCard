package com.wedding.card.service;

import com.wedding.card.dto.OrderDTO;
import com.wedding.card.entity.Member;
import com.wedding.card.entity.Order;
import com.wedding.card.repository.MemberRepository;
import com.wedding.card.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    public Order toOrder(OrderDTO orderDTO) {       //DTO to Entity

        Member member = memberRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원이 없습니다."));


        return Order.builder()
                .orderDate(orderDTO.getOrderDate())
                .amount(orderDTO.getAmount())
                .member(member)
                .merchantUid(orderDTO.getMerchantUid())
                .paymentStatus(orderDTO.getPaymentStatus())
                .productName(orderDTO.getProductName())
                .build();
    }




    public void createOrder(OrderDTO orderDTO) {
        Member member = memberRepository.findById(orderDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .productName(orderDTO.getProductName())
                .paymentStatus(orderDTO.getPaymentStatus())
                .merchantUid(orderDTO.getMerchantUid())
                .amount(orderDTO.getAmount())
                .member(member)
                .impUid(orderDTO.getImpUid())
                .build();

        orderRepository.save(order);
    }
}




