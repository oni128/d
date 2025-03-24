package com.microsoftwo.clother.adviceBoard.command.application.controller;

import com.microsoftwo.clother.adviceBoard.command.application.dto.BoardRequestDTO;
import com.microsoftwo.clother.adviceBoard.command.application.service.BoardCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@Slf4j
public class BoardCommandController {

    private BoardCommandService boardCommandService;

    public BoardCommandController(BoardCommandService boardCommandService) {
        this.boardCommandService = boardCommandService;
    }

    //게시글 등록
    @PostMapping("/create")
    // ResponseEntity -- Spring에서 HTTP 응답을 생성하는 객체
    public ResponseEntity<BoardRequestDTO> createBoard(@RequestBody BoardRequestDTO request ) {

        BoardRequestDTO savedBoard = boardCommandService.createBoard(request.getUserId(),request.getTitle(),request.getContent()
                                                                        ,request.getImages());
//        for (BoardRequestDTO.BoardImageDTO dto : request.getImages()) {
//            log.info("저장 중: imageUrl={}, order={}", dto.getImageUrl(), dto.getOrder());
//        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);
    }
}


