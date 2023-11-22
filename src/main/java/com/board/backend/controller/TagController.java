package com.board.backend.controller;

import com.board.backend.dto.PostRegDto;
import com.board.backend.dto.TagRegDto;
import com.board.backend.dto.TagResDto;
import com.board.backend.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 태그 Controller
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Slf4j
@Tag(name = "태그")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class TagController {

  private final TagService tagService;

  @Operation(summary = "태그 등록 API", description = "요청을 받아 등록 후 태그ID를 반환합니다.")
  @PostMapping("/tag")
  public ResponseEntity<Integer> regTag(@RequestBody TagRegDto tagRegDto) {
    log.info("태그 등록 with tag = {}", tagRegDto.getTag());
    int tagNo = tagService.regTag(tagRegDto);
    return ResponseEntity.ok().body(tagNo);
  }
}
