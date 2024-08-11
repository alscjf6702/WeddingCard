package com.wedding.card.repository;

import com.wedding.card.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long > {

    Boolean existsByUsername(String username);

    Boolean existsByMemberNo(Integer memberNo);

}
