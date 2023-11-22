package com.board.backend.controller;

import com.board.backend.dto.DefDto;
import com.board.backend.dto.PostAndTagsResDto;
import com.board.backend.model.entity.BoardDef;
import com.board.backend.service.DefService;
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
 * 게시판 분류정보 관련 Controller
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

  @Operation(summary = "게시판 분류정보 등록 API", description = "등록된 분류정보ID를 반환합니다.")
  @PostMapping("/def")
  public ResponseEntity<String> regDef(@RequestBody DefDto defDto) {
    log.info("게시판 분류정보 등록 시작");
    String boardCd = defService.regDef(defDto);
    return ResponseEntity.ok().body(boardCd);
  }

  @Operation(summary = "모든 게시판 분류정보 조회")
  @GetMapping("/def")
  public ResponseEntity<List<DefDto>> getAllDef() {
    log.info("모든 게시판 분류정보 조회 시작");
    List<DefDto> defDtoList = defService.getAllDef();
    return ResponseEntity.ok().body(defDtoList);
  }

  @Operation(summary = "게시판 분류정보 세부조회", description = "게시판 분류 정보에 해당하는 게시물정보를 반환합니다")
  @GetMapping("/def/{boardCd}")
  public ResponseEntity<List<PostAndTagsResDto>> getDefWithBoard(@PathVariable String boardCd) {
    log.info("게시판 분류정보 세부조회 시작 with boardCd = {}", boardCd);
    List<PostAndTagsResDto> boardList = defService.getDefWithBoard(boardCd);
    return ResponseEntity.ok().body(boardList);
  }
}
