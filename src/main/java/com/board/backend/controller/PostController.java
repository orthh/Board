package com.board.backend.controller;

import com.board.backend.dto.PostAndTagsDto;
import com.board.backend.dto.PostRegDto;
import com.board.backend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 게시물 Controller
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Slf4j
@Tag(name = "게시물")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class PostController {

  private final PostService postService;

  @Operation(summary = "게시물 등록 API", description = "요청을 받아 등록 후 게시물번호를 반환합니다.")
  @PostMapping("/post")
  public ResponseEntity<Integer> regPost(@RequestBody PostAndTagsDto postAndTagsDto) {
    log.info("게시물 등록 with regstrId = {}", postAndTagsDto.getPost().getRegstrId());
    int postNo = postService.regPost(postAndTagsDto.getPost(), postAndTagsDto.getTags());
    return ResponseEntity.ok().body(postNo);
  }
}
