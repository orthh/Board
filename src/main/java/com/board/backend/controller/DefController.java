package com.board.backend.controller;

import com.board.backend.dto.DefRegDto;
import com.board.backend.service.DefService;
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
 * 게시판 분류정보 Controller
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Slf4j
@Tag(name = "게시판 분류정보")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class DefController {

  private final DefService defService;

  @Operation(summary = "게시판 분류정보 등록 API")
  @PostMapping("/def")
  public ResponseEntity<String> regDef(@RequestBody DefRegDto defRegDto) {
    log.info("게시판 분류정보 등록 시작");
    String boardCd = defService.regDef(defRegDto);
    return ResponseEntity.ok().body(boardCd);
  }
}
