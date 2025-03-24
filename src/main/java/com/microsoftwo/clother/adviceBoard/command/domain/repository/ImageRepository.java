package com.microsoftwo.clother.adviceBoard.command.domain.repository;

import com.microsoftwo.clother.adviceBoard.command.domain.aggregate.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
}
