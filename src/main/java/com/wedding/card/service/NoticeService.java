package com.wedding.card.service;

import ch.qos.logback.classic.Logger;
import com.wedding.card.dto.NoticeDTO;
import com.wedding.card.entity.Notice;
import com.wedding.card.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<Notice> noticeList(Pageable pageable) {
        Sort sort1 = Sort.by("id").descending();        //글번호에 따른 정렬
        Sort sort2 = Sort.by("required").descending();  //중요도에 따른 정렬

        Sort sortAll = sort2.and(sort1);            //중요도가 먼저, 그 다음 글번호 정렬
        pageable = PageRequest.of(pageable.getPageNumber(), 10, sortAll);

        return noticeRepository.findAll(pageable);
    }

    public void writeNotice(NoticeDTO dto) {
        Notice notice = Notice.builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .required(dto.getRequired())
                .build();

        noticeRepository.save(notice);
    }

    public Notice findDetail(Long id){
        Optional<Notice> byId = noticeRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        }else {
            throw new IllegalArgumentException("해당 글 번호는 존재하지 않습니다: " + id);
        }
    }

    public void deleteNotice(Long id){
        noticeRepository.deleteById(id);

    }

    public Notice updateNotice(NoticeDTO dto){


        Notice notice = noticeRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 번호는 존재하지 않습니다."));

        notice.update(dto.getTitle(), dto.getContent(), dto.getRequired());
        return noticeRepository.save(notice);
    }

    public void plusReadCount(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호는 존재하지 않습니다."));

        notice.plusReadCount();
        noticeRepository.save(notice);

    }


}
