package com.board.backend.controller;

import com.board.backend.dto.PostAndTagsDto;
import com.board.backend.dto.PostAndTagsResDto;
import com.board.backend.dto.PostRegDto;
import com.board.backend.dto.PostResDto;
import com.board.backend.model.entity.Post;
import com.board.backend.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @Operation(summary = "게시물 등록 API", description = "등록된 게시물번호를 반환합니다.")
  @PostMapping("/post")
  public ResponseEntity<Integer> regPost(@RequestBody PostAndTagsDto postAndTagsDto) {
    log.info("게시물 등록 with regstrId = {}", postAndTagsDto.getPost().getRegstrId());
    int postNo = postService.regPost(postAndTagsDto.getPost(), postAndTagsDto.getTags());
    return ResponseEntity.ok().body(postNo);
  }

  @Operation(summary = "게시글 수정 API", description = "수정된 게시물 번호를 반환합니다.")
  @PutMapping("/post/{postNo}")
  public ResponseEntity<Integer> updatePost(
      @PathVariable int postNo, @RequestBody PostRegDto postRegDto) {
    log.info("게시글 수정 with postNo = {}", postNo);
    int udtPostNo = postService.updatePost(postNo, postRegDto);
    return ResponseEntity.ok().body(udtPostNo);
  }

  @Operation(summary = "tagNo에 해당하는 게시물 조회 API")
  @GetMapping("/post")
  public ResponseEntity<List<PostResDto>> getPostsByTagNo(@RequestParam String tagNo) {
    log.info("게시글 조회 시작 with tagNo = {}", tagNo);
    List<PostResDto> posts = postService.getPostsByTagNo(Integer.parseInt(tagNo));
    return ResponseEntity.ok(posts);
  }

  @Operation(summary = "게시글 삭제 API")
  @DeleteMapping("/post/{postNo}")
  public ResponseEntity<Integer> deletePost(@PathVariable String postNo) {
    log.info("게시글 삭제 with postNo = {}", postNo);
    int no = postService.deletePost(postNo);
    return ResponseEntity.ok().body(no);
  }
}
