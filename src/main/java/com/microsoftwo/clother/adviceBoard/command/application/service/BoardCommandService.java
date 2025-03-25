package com.microsoftwo.clother.adviceBoard.command.application.service;


import com.microsoftwo.clother.adviceBoard.command.application.dto.BoardRequestDTO;

import java.util.List;

public interface BoardCommandService {
    BoardRequestDTO createBoard(int userId, String title, String content, List<BoardRequestDTO.BoardImageDTO> images);

    void deleteBoard(int boardId);

    BoardRequestDTO updateBoard(int postid, BoardRequestDTO request);
}
