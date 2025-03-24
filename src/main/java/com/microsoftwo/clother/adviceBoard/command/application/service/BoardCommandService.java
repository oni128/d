package com.microsoftwo.clother.adviceBoard.command.application.service;

import com.microsoftwo.clother.adviceBoard.command.application.dto.BoardCommandDTO;
import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.BoardEntity;
import com.microsoftwo.clother.adviceBoard.command.domain.repository.BoardCommandRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class BoardCommandService {

    private final BoardCommandRepository boardCommandRepository;

    @Autowired
    public BoardCommandService(BoardCommandRepository boardCommandRepository) {
        this.boardCommandRepository = boardCommandRepository;
    }


    public BoardCommandDTO createBoard(BoardCommandDTO boardCommandDTO) {
        BoardEntity boardEntity = boardCommandDTO.toEntity();
        BoardEntity savedEntity = boardCommandRepository.save(boardEntity);
        return BoardCommandDTO.fromEntity(savedEntity);
    }
}
