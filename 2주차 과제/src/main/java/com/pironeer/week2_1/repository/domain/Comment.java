package com.pironeer.week2_1.repository.domain;

import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long topicId;
    private Long parentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Comment(
            Long id,
            Long topicId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.topicId = topicId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment update(CommentUpdateRequest request) {
        this.content = request.content();
        this.updatedAt = LocalDateTime.now();
        return this;
    }
}
