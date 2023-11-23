package com.board.backend.service;

import com.board.backend.dto.DefDto;
import com.board.backend.dto.PostAndTagsResDto;
import com.board.backend.dto.TagResDto;
import com.board.backend.model.entity.BoardDef;
import com.board.backend.model.entity.Post;
import com.board.backend.model.entity.PostTag;
import com.board.backend.model.entity.Tag;
import com.board.backend.repository.DefRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
   * @throws DataIntegrityViolationException 기본키로 등록된 게시판 분류코드 중복시 에러발생
   * @param defRegDto
   * @return boardCd
   */
  @Transactional
  public String regDef(DefDto defDto) {
    try {
      return defRepository.save(defDto.toEntity()).getBoardCd();
    } catch (DataIntegrityViolationException e) {
      throw new RuntimeException("중복된 게시판 코드입니다. 다른 코드를 입력해주세요.");
    }
  }

  /**
   * 모든 게시물 분류 조회
   *
   * @return List<DefDto>
   */
  public List<DefDto> getAllDef() {
    return defRepository.findAll().stream().map(DefDto::new).collect(Collectors.toList());
  }

  /**
   * 게시물 분류에 해당하는 모든 게시물과 태그 조회
   *
   * @param boardCd
   * @return List<PostAndTagsResDto>
   */
  public List<PostAndTagsResDto> getDefWithBoard(String boardCd) {
    BoardDef boardDef =
        defRepository
            .findById(boardCd)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 게시판코드가 없습니다."));
    List<Post> posts = boardDef.getPosts();

    List<PostAndTagsResDto> resList = new ArrayList<>();
    for (Post post : posts) {
      List<PostTag> postTags = post.getPostTags();
      PostAndTagsResDto dto =
          PostAndTagsResDto.builder()
              .postNo(post.getPostNo())
              .postSj(post.getPostSj())
              .postCn(post.getPostCn())
              .regstrId(post.getRegstrId())
              .build();

      List<TagResDto> tagResDtos = new ArrayList<>();
      for (PostTag postTag : postTags) {
        TagResDto tagResDto = new TagResDto(postTag.getTag());
        tagResDtos.add(tagResDto);
      }
      dto.setTags(tagResDtos);
      resList.add(dto);
    }

    return resList;
  }
}
