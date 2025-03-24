package com.microsoftwo.clother.adviceBoard.command.application.dto;

import java.time.LocalDateTime;

public class PostRequestDTO {
    private int id;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Boolean isDeleted;
    private int likeCount;
    private int commentCount;
}
