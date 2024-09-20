package com.pironeer.week2_1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentCreateRequest(
        @NotNull
        @Schema(description = "게시물 ID", example = "1")
        Long topicId,

        @NotBlank
        @Schema(description = "댓글 내용", example = "댓글 내용입니다.")
        String content
) {
}
