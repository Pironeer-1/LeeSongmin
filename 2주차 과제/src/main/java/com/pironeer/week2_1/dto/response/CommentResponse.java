package com.pironeer.week2_1.dto.response;

import com.pironeer.week2_1.repository.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CommentResponse {
    // Getters
    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "게시물 ID", example = "1")
    private Long topicId;

    @Schema(description = "부모 댓글 ID", example = "2")
    private Long parentId;

    @Schema(description = "댓글 내용", example = "댓글 내용입니다.")
    private String content;

    @Schema(description = "댓글 생성 시간", example = "2024-10-10 10:10:00")
    private LocalDateTime createdAt;

    @Schema(description = "댓글 수정 시간", example = "2024-10-13 10:10:00")
    private LocalDateTime updatedAt;

    @Schema(description = "대댓글 목록")
    private List<CommentResponse> replies;

    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .topicId(comment.getTopicId())
                .parentId(comment.getParentId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .replies(new ArrayList<>())
                .build();
    }

}
