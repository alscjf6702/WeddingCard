package com.wedding.card.dto;

import com.wedding.card.entity.Notice;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Transactional
public class NoticeDTO {
    private Long id;        //글번호

    private String title;   //제목

    private String content; //내용

    private int readCount;  //조회수

    private int required;  //중요도 (0~2)

    private String picPath;     //사진경로

    private String picName;     //사진이름

    public Notice toEntity(){
        return Notice.builder()
                .content(content)
                .title(title)
                .required(required)
                .build();

    }

}
