package com.wedding.card.service;

import com.wedding.card.dto.OrderDTO;
import com.wedding.card.entity.Member;
import com.wedding.card.entity.Order;
import com.wedding.card.repository.InfoRepository;
import com.wedding.card.repository.MemberRepository;
import com.wedding.card.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final InfoRepository infoRepository;

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = infoRepository.findByUsername(username);


        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .productName(orderDTO.getProductName())
                .paymentStatus(orderDTO.getPaymentStatus())
                .merchantUid(orderDTO.getMerchantUid())
                .amount(orderDTO.getAmount())
                .member(member)
                .impUid(orderDTO.getImpUid())
                .build();

        member.setOrderState("paid");

        orderRepository.save(order);
    }
}




