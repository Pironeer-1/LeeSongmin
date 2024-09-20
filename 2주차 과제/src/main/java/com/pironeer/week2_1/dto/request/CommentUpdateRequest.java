package com.pironeer.week2_1.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentUpdateRequest(
        @NotNull
        @Schema(description = "댓글 ID", example = "1")
        Long id,

        @NotBlank
        @Schema(description = "수정할 댓글 내용", example = "수정된 댓글 내용입니다.")
        String content
) {
}
