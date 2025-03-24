package com.microsoftwo.clother.adviceBoard.query.service;

import com.microsoftwo.clother.adviceBoard.query.dao.BoardMapper;
import com.microsoftwo.clother.adviceBoard.query.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
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

    public BoardDTO getBoardById(int id) {
        return boardMapper.getBoradById(id);
    }

    public List<BoardDTO> searchBoards(String sortBy, String keyword) {

        // 허용된 정렬 기준 목록
        Set<String> validSortOptions = Set.of("title", "content", "title+content");

        // 유효한 값인지 검증 (유효하지 않으면 기본값 설정)
        if (validSortOptions == null | !validSortOptions.contains(sortBy)) {
            sortBy = "title+content";
        }
        return boardMapper.searchBoards(sortBy, keyword);
    }
}
