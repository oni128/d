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

    @GetMapping
    public List<BoardDTO> getBoards(@RequestParam(required = false) String sortBy) {
        return boardQueryService.getBoards(sortBy);
    }



}

