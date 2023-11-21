package com.board.backend.service;

import com.board.backend.dto.DefRegDto;
import com.board.backend.model.entity.BoardDef;
import com.board.backend.repository.DefRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시판 분류정보 관련 Service
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefService {

  private final DefRepository defRepository;

  /**
   * 게시판 분류 정보 등록
   *
   * @throws DataIntegrityViolationException 기본키로 등록된 게시판 분류코드 중복시 에러처리
   * @param defRegDto
   * @return boardCd
   */
  @Transactional
  public String regDef(DefRegDto defRegDto) {
    try {
      return defRepository.save(defRegDto.toEntity()).getBoardCd();
    } catch (DataIntegrityViolationException e) {
      throw new RuntimeException("중복된 게시판 코드입니다. 다른 코드를 입력해주세요.");
    }
  }

  /**
   * 게시판코드를 기반으로 게시판분류 조회
   *
   * @param boardCd
   * @return BoardDef
   */
  public BoardDef findByBoardCd(String boardCd) {
    return defRepository
        .findById(boardCd)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시판 코드가 없습니다. boardCd: " + boardCd));
  }
}
