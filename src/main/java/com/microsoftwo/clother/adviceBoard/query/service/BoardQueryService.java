package com.microsoftwo.clother.adviceBoard.query.service;

import com.microsoftwo.clother.adviceBoard.query.dao.BoardMapper;
import com.microsoftwo.clother.adviceBoard.query.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardQueryService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardQueryService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<BoardDTO> getBoards(String sortBy) {
        // 기본값: 최신순
        if (!"like".equals(sortBy)) {
            sortBy = "created_at";
        }
        return boardMapper.getBoards(sortBy);
    }
}
