package com.microsoftwo.clother.adviceBoard.command.domain.aggregate;

import com.microsoftwo.clother.adviceBoard.command.application.dto.BoardRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "advice_board_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity boardId;    // 어떤 게시글에 속한 이미지인지

    @Column(nullable = false)
    private String imageUrl;

    @Column(name = "`order`", nullable = false)
    private int order;

    // ✅ setBoard 메서드 추가 (양방향 연관관계 설정)
    public void setBoard(BoardEntity boardId) {
        this.boardId = boardId;
    }

//    public BoardRequestDTO.BoardImageDTO toDto() {
//        return BoardRequestDTO.BoardImageDTO.builder()
//                .id(this.id)
//                .boardId(this.boardId.getId()) // board 엔티티가 null이 아닌지 확인 필요
//                .imageUrl(this.imageUrl)
//                .order(this.order)
//                .build();
//    }
    public BoardRequestDTO.BoardImageDTO toDto() {
        return BoardRequestDTO.BoardImageDTO.builder()
                .id(this.id)
                .boardId(this.boardId != null ? this.boardId.getId() : 0) // null 체크,  ✅ board가 null이 아닐 때만 접근
                .imageUrl(this.imageUrl)
                .order(this.order)
                .build();
}
}
