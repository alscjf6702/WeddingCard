package com.wedding.card.repository;

import com.wedding.card.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Member, Long> {

    <Optional>Member findByUsername(String username);

}
