package com.board.backend.service;

import com.board.backend.model.entity.BoardDef;
import com.board.backend.model.entity.Post;
import com.board.backend.model.entity.PostTag;
import com.board.backend.model.entity.Tag;
import com.board.backend.repository.PostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시물 태그 관련 Service
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostTagService {

  private final PostTagRepository postTagRepository;

  /**
   * 게시물 태그 등록
   *
   * @param post
   * @param boardDef
   * @param tag
   * @return boardTagId
   */
  @Transactional
  public int regPostTag(Post post, BoardDef boardDef, Tag tag) {
    PostTag postTag = PostTag.builder().post(post).boardDef(boardDef).tag(tag).build();
    return postTagRepository.save(postTag).getBoardTagId();
  }
}
