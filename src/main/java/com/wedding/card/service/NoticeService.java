package com.wedding.card.service;

import com.wedding.card.dto.NoticeDTO;
import com.wedding.card.entity.Notice;
import com.wedding.card.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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

}
