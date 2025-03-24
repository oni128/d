package com.microsoftwo.clother.adviceBoard.command.application.dto;

import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.BoardEntity;
import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.ImageEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class BoardRequestDTO {
    private int id;             // 게시물 id
    private int userId;         // 회원 id
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Boolean isDeleted;
    private int likeCount;
    private int commentCount;
    private List<BoardImageDTO> images;

/* 왜 필요가 없을까?*/
//    public BoardRequestDTO(int userId, String title, String content, List<BoardImageDTO> images) {
//        this.userId = userId;
//        this.title = title;
//        this.content = content;
//        this.images = images;
//    }

    public static BoardRequestDTO fromEntity(BoardEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("BoardEntity가 null입니다.");
        }
        log.info("✅ fromEntity 호출됨! 게시글 ID: {}", entity.getId());

        List<BoardRequestDTO.BoardImageDTO> imageDTOs = new ArrayList<>();
        if (entity.getImages() != null) {
            log.info("✅ entity.getImages() 개수: {}", entity.getImages().size());
            imageDTOs = entity.getImages().stream()
                    .map(ImageEntity::toDto)
                    .collect(Collectors.toList());
        } else {
            log.warn("🚨 entity.getImages()가 null 입니다!");
        }


        return BoardRequestDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .isDeleted(entity.getIsDeleted())
                .likeCount(entity.getLikeCount())
                .commentCount(entity.getCommentCount())
                .images(entity.getImages()
                        .stream()
                        .map(ImageEntity::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    // DTO → 엔티티 변환 메서드
    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .id(this.id)
                .userId(this.userId)
                .title(this.title)
                .content(this.content)
                .createdAt(this.createdAt != null ? this.createdAt : LocalDateTime.now())
                .isDeleted(this.isDeleted != null ? this.isDeleted : false)
                .likeCount(this.likeCount)
                .commentCount(this.commentCount)
                .build();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BoardImageDTO {
        private int id;
        private int boardId;
        private String imageUrl;
        private int order;

        public BoardImageDTO(int boardId, String imageUrl, int order) {
            this.boardId = boardId;
            this.imageUrl = imageUrl;
            this.order = order;
        }
    }
}
