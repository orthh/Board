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
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @Operation(summary = "태그 삭제 API", description = "요청을 받아 삭제 후 삭제된 태그No를 반환합니다.")
  @DeleteMapping("/tag/{tagNo}")
  public ResponseEntity<Integer> deleteTag(@PathVariable int tagNo) {
    log.info("태그 삭제 with tagNo = {}", tagNo);
    int deletedTagNo = tagService.deleteTag(tagNo);
    return ResponseEntity.ok().body(deletedTagNo);
  }
}
