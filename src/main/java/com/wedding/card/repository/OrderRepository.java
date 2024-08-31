package com.wedding.card.repository;

import com.wedding.card.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByMemberId(Long memberId);

    List<Order> findALLByProductCode(String productCode);

}
