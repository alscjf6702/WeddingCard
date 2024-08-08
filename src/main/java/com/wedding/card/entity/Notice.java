package com.wedding.card.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        //글번호

    @Column(nullable = false)
    private String title;   //제목

    @Column(nullable = false)
    private String content; //내용

    @ColumnDefault("0")
    private int readCount;  //조회수

    private Timestamp createDate; //작성날짜

    @ColumnDefault("0")
    private int required;  //중요도 (0~2)

    private String picPath;     //사진경로

    private String picName;     //사진이름

    @PrePersist
    protected void onCreate(){
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    public void update(String title, String content, int required) {
        this.title = title;
        this.content = content;
        this.required = required;
    }

    public void plusReadCount() {
        this.readCount++;
    }

}
