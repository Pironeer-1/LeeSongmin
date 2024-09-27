package com.pironeer.mytemplatecode.board.service;

import com.pironeer.mytemplatecode.board.dto.request.BoardUpdateRequest;
import com.pironeer.mytemplatecode.board.dto.response.BoardResponse;
import com.pironeer.mytemplatecode.board.entity.Board;
import com.pironeer.mytemplatecode.board.mapper.BoardMapper;
import com.pironeer.mytemplatecode.board.repository.BoardRepository;
import com.pironeer.mytemplatecode.global.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public SingleResult<Long> save(BoardRepository request) {
        Board savedBoard = boardRepository.save(BoardMapper.from(request));
        return ResponseService.getSingleResult(savedBoard.getId());
    }

    public SingleResult<BoardResponse> findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        BoardResponse boardResponse = BoardResponse.of(board);
        return ResponseService.getSingleResult(boardResponse);
    }

    public ListResult<BoardResponse> findAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardResponse> list = boards.stream().map(BoardResponse::of).toList();
        return ResponseService.getListResult(list);
    }

    public BoardResponse update(BoardUpdateRequest request) {
        Board board = boardRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("TOPIC NOT FOUND"));
        boardRepository.save(board.update(request));
        return BoardResponse.of(board);
    }

    public Long deleteById(Long id) {
        Long deleteId = boardRepository.deleteById(id);
        return deleteId;
    }
}

