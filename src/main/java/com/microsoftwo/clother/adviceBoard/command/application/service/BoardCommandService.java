package com.microsoftwo.clother.adviceBoard.command.application.service;


import com.microsoftwo.clother.adviceBoard.command.application.dto.BoardRequestDTO;

import java.util.List;

public interface BoardCommandService {
    BoardRequestDTO createBoard(int userId, String title, String content, List<BoardRequestDTO.BoardImageDTO> images);

//    BoardRequestDTO createBoard(int userId, String title, String content, List<BoardImageDTO> imageUrl, List<BoardImageDTO> order);
}
