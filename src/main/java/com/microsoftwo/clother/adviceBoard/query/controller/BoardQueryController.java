package com.microsoftwo.clother.adviceBoard.query.controller;

import com.microsoftwo.clother.adviceBoard.query.dto.BoardDTO;
import com.microsoftwo.clother.adviceBoard.query.service.BoardQueryService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@Data
public class BoardQueryController {

    private final BoardQueryService boardQueryService;
    // 게시판 조회
    @GetMapping
    public List<BoardDTO> getBoards(@RequestParam(required = false) String sortBy) {
        System.out.println("sortBy" + sortBy);
        return boardQueryService.getBoards(sortBy);
    }



}

