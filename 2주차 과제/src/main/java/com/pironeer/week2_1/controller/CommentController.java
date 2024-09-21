package com.pironeer.week2_1.controller;

import com.pironeer.week2_1.dto.request.CommentCreateRequest;
import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import com.pironeer.week2_1.dto.response.CommentResponse;
import com.pironeer.week2_1.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글(Comment)")
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "댓글 작성")
    public ResponseEntity<?> create(@Valid @RequestBody CommentCreateRequest request) {
        commentService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{commentId}")
    @Operation(summary = "댓글 단건 조회")
    public ResponseEntity<CommentResponse> read(@PathVariable("commentId") Long id) {
        CommentResponse response = commentService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/topic/{topicId}")
    @Operation(summary = "특정 게시물의 모든 댓글 조회")
    public ResponseEntity<List<CommentResponse>> readByTopic(@PathVariable("topicId") Long topicId) {
        List<CommentResponse> responses = commentService.findByTopicId(topicId);
        return ResponseEntity.ok().body(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @Valid @RequestBody CommentUpdateRequest request) {
        if (!id.equals(request.id())) {
            throw new IllegalArgumentException("Path variable id and request body id do not match");
        }
        CommentResponse response = commentService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<?> remove(@PathVariable("commentId") Long id) {
        commentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
