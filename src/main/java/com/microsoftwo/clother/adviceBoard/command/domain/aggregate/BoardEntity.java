package com.microsoftwo.clother.adviceBoard.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advice_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (AUTO_INCREMENT)
    private int id;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(nullable = false)
    private int likeCount = 0;

    @Column(nullable = false)
    private int commentCount = 0;

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.ALL, orphanRemoval = true) // ✅ 게시물과 1:N 관계 설정
    private List<ImageEntity> images = new ArrayList<>();

    public List<ImageEntity> getImages() {
        return images == null ? new ArrayList<>() : images; // ✅ null 방지
    }

    // ✅ 이미지 리스트 추가 메서드
    public void addImage(ImageEntity image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
        image.setBoard(this);  // ✅ 양방향 연관관계 설정
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
        this.likeCount = 0;
        this.commentCount = 0;
    }

}
