package com.pironeer.week2_1.service;

import com.pironeer.week2_1.dto.request.CommentCreateRequest;
import com.pironeer.week2_1.dto.request.CommentUpdateRequest;
import com.pironeer.week2_1.dto.response.CommentResponse;
import com.pironeer.week2_1.mapper.CommentMapper;
import com.pironeer.week2_1.repository.CommentRepository;
import com.pironeer.week2_1.repository.TopicRepository;
import com.pironeer.week2_1.repository.domain.Comment;
import com.pironeer.week2_1.repository.domain.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TopicRepository topicRepository;

    public void save(CommentCreateRequest request) {
        Topic topic = topicRepository.findById(request.topicId())
                .orElseThrow(() -> new RuntimeException("TOPIC NOT FOUND"));

        Comment comment = CommentMapper.from(request);
        commentRepository.save(comment);
    }

    public CommentResponse findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("COMMENT NOT FOUND"));
        return CommentMapper.toResponse(comment);
    }

    public List<CommentResponse> findByTopicId(Long topicId) {
        List<Comment> comments = commentRepository.findByTopicId(topicId);
        Map<Long, CommentResponse> commentResponseMap = new HashMap<>();

        for (Comment comment : comments) {
            CommentResponse response = CommentMapper.toResponse(comment);
            commentResponseMap.put(comment.getId(), response);

            if (comment.getParentId() != null) {
                CommentResponse parentResponse = commentResponseMap.get(comment.getParentId());
                if (parentResponse != null) {
                    parentResponse.getReplies().add(response);
                }
            }
        }

        return commentResponseMap.values().stream()
                .filter(response -> response.getParentId() == null)
                .toList();
    }

    public CommentResponse update(CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("COMMENT NOT FOUND"));

        comment.update(request);
        commentRepository.save(comment);

        return CommentMapper.toResponse(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
