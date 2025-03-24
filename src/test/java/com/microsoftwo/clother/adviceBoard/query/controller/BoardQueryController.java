package com.microsoftwo.clother.adviceBoard.query.controller;

import com.microsoftwo.clother.adviceBoard.query.dto.BoardDTO;
import com.microsoftwo.clother.adviceBoard.query.service.BoardQueryService;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@Data
@Slf4j
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    // 게시판 조회
    @GetMapping
    public List<BoardDTO> getBoards(@RequestParam(required = false) String sortBy) {
        return boardQueryService.getBoards(sortBy);
    }

    // 게시물 조회
    @GetMapping("/{id}")
    public BoardDTO getBoardById(@PathVariable int id) {
        return boardQueryService.getBoardById(id);
    }
    // 검색 (제목순,내용순, 제목+내용순) + 최신순
    @GetMapping("/search")
    public List<BoardDTO> searchBoards(@RequestParam(required = false) String sortBy,
                                       @RequestParam(required = false) String keyword) {

        if (StringUtils.isBlank(keyword)) { // null 또는 빈 문자열 체크
            return boardQueryService.getBoards(sortBy);
        }
        return boardQueryService.searchBoards(sortBy, keyword);
    }
}

