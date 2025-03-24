package com.microsoftwo.clother.adviceBoard.command.domain.repository;

import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommandRepository extends JpaRepository<BoardEntity, Integer> {

    BoardEntity save(BoardEntity boardEntity);
}
