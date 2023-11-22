package com.board.backend.service;

import com.board.backend.dto.TagRegDto;
import com.board.backend.model.entity.BoardDef;
import com.board.backend.model.entity.Tag;
import com.board.backend.repository.DefRepository;
import com.board.backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 태그 관련 Service
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

  private final TagRepository tagRepository;
  private final DefRepository defRepository;

  /**
   * 태그 등록
   *
   * @param tagRegDto
   * @return tagNo
   */
  @Transactional
  public int regTag(TagRegDto tagRegDto) {
    BoardDef boardDef =
        defRepository
            .findById(tagRegDto.getBoardCd())
            .orElseThrow(() -> new IllegalArgumentException("해당 게시판분류정보가 없습니다. boardCd: " + tagRegDto.getBoardCd()));
    return tagRepository.save(tagRegDto.toEntity(boardDef)).getTagNo();
  }

  /**
   * 태그ID를 기반으로 태그 조회
   *
   * @param tagNo
   * @return Tag
   */
  public Tag findByTagNo(int tagNo) {
    return tagRepository
        .findById(tagNo)
        .orElseThrow(() -> new IllegalArgumentException("해당 태그가 없습니다. tagNo: " + tagNo));
  }
}
