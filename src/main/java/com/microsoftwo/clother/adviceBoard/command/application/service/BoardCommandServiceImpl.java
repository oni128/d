package com.microsoftwo.clother.adviceBoard.command.application.service;


import com.microsoftwo.clother.adviceBoard.command.application.dto.BoardRequestDTO;
import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.BoardEntity;
import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.ImageEntity;
import com.microsoftwo.clother.adviceBoard.command.domain.repository.BoardCommandRepository;
import com.microsoftwo.clother.adviceBoard.command.domain.repository.ImageRepository;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@NoArgsConstructor
@Slf4j
@Builder
public class BoardCommandServiceImpl implements BoardCommandService {

    private BoardCommandRepository boardCommandRepository;
    private ImageRepository imageRepository;

    @Autowired
    public BoardCommandServiceImpl(BoardCommandRepository boardCommandRepository, ImageRepository imageRepository) {
        this.boardCommandRepository = boardCommandRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public BoardRequestDTO createBoard(int userId, String title, String content, List<BoardRequestDTO.BoardImageDTO> images) {
        //게시글 생성
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setUserId(userId);
        boardEntity.setTitle(title);
        boardEntity.setContent(content);


        // 게시글 저장
        BoardEntity savedEntity = boardCommandRepository.save(boardEntity);

        // 이미지 저장 (리스트에서 하나씩 꺼내어 저장)
        if (images != null) {
            for (BoardRequestDTO.BoardImageDTO dto : images) {
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setImageUrl(dto.getImageUrl());
                imageEntity.setOrder(dto.getOrder());
                imageEntity.setBoardId(savedEntity);    // 연관관계 설정 -- imageEntity가 어느 BoardEntity에 속하는지 설정
                savedEntity.addImage(imageEntity);
                // 양방향 연관관계 설정 -- savedEntity(BoardEntity)에도 imageEntity를 추가하여 객체 관계 동기화
                // addImage() -- BoardEntity 내부에서 List<ImageEntity>에 추가하는 역할
                imageRepository.save(imageEntity);
            }
        } else {
            log.warn("🚨 저장할 이미지가 없습니다.");
        }

        // 게시글 엔티티를 다시 저장 (연관관계 반영)
        boardCommandRepository.save(savedEntity);


        // 트랜잭션 종료 시점(commit)
        return BoardRequestDTO .fromEntity(savedEntity);
    }

}

