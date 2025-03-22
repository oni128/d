package com.microsoftwo.clother.adviceBoard.query.dao;

import com.microsoftwo.clother.adviceBoard.query.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDTO> getBoards(@Param("sortBy") String sortBy);

}
