package com.microsoftwo.clother.adviceBoard.command.application.dto;

import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCommandDTO {
    private int id;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Boolean isDeleted;
    private int likeCount;
    private int commentCount;

    // 엔티티 → DTO 변환 메서드
    public static BoardCommandDTO fromEntity(BoardEntity entity) {
        return BoardCommandDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .isDeleted(entity.getIsDeleted())
                .likeCount(entity.getLikeCount())
                .commentCount(entity.getCommentCount())
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
}

