package com.wedding.card.service;

import com.wedding.card.dto.MemberDTO;
import com.wedding.card.entity.Card;
import com.wedding.card.entity.Member;
import com.wedding.card.entity.Order;
import com.wedding.card.repository.CardRepository;
import com.wedding.card.repository.InfoRepository;
import com.wedding.card.repository.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final InfoRepository infoRepository;

    private final OrderRepository orderRepository;

    private final CardRepository cardRepository;

    public class ProductPurchaseException extends RuntimeException {
        public ProductPurchaseException(String message) {
            super(message);
        }
    }


    public Member findByUsername(String username){
        return infoRepository.findByUsername(username);
    }

    public void updateMember(MemberDTO dto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = infoRepository.findByUsername(username);

        member.setMemberAddress(dto.getMemberAddress());
        member.setEmail(dto.getEmail());

        infoRepository.save(member);
    }

    public void DeleteMember(MemberDTO dto, HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = infoRepository.findByUsername(username);

        Long id = member.getId();

        infoRepository.deleteById(id);

        new SecurityContextLogoutHandler().logout(request, response, authentication);
    }

    public Card purchasedProduct() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Member member = infoRepository.findByUsername(username);

        String orderState = member.getOrderState();
        if (Objects.equals(orderState, "paid")) {
            Long memberId = member.getId();

            Order order = orderRepository.findByMemberId(memberId);
                String productCode = order.getProductCode();
                return cardRepository.findByProductCode(productCode);

        }else {
            return null;
        }
    }

}
