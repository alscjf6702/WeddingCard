package com.wedding.card.service;

import com.wedding.card.entity.Member;
import com.wedding.card.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final InfoRepository infoRepository;


    public Member findByUsername(String username){
        return infoRepository.findByUsername(username);
    }


}
