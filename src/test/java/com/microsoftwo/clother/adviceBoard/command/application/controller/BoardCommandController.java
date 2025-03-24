package com.microsoftwo.clother.adviceBoard.command.application.controller;

import com.microsoftwo.clother.adviceBoard.command.application.dto.BoardCommandDTO;
import com.microsoftwo.clother.adviceBoard.command.application.service.BoardCommandService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardCommandController {

    private final BoardCommandService boardService;

    @Autowired
    public BoardCommandController(BoardCommandService boardService) {
        this.boardService = boardService;
    }

    // 게시글 등록
    @PostMapping("/create")
    public ResponseEntity<BoardCommandDTO> createBoard(@RequestBody BoardCommandDTO boardCommandDTO) {
        BoardCommandDTO savedBoard = boardService.createBoard(boardCommandDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);
    }
}

