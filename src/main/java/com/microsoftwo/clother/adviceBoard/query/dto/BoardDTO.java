package com.microsoftwo.clother.adviceBoard.query.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
// 조회용 데이터를 반환할 DTO 클래스 (게시글 상세 조회)
public class BoardDTO {
    private int id;                     // 게시물 id
    private String title;               // 제목
    private String imageUrl;            // 사진 url
    private String content;             // 내용
    private String userId;              // 작성자
    private boolean isDeleted;
    private LocalDateTime createdAt;    // 작성날짜
}
