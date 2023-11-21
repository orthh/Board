package com.board.backend.service;

import com.board.backend.dto.PostRegDto;
import com.board.backend.model.entity.BoardDef;
import com.board.backend.model.entity.Post;
import com.board.backend.model.entity.PostTag;
import com.board.backend.model.entity.Tag;
import com.board.backend.repository.DefRepository;
import com.board.backend.repository.PostRepository;
import com.board.backend.repository.PostTagRepository;
import com.board.backend.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시물 관련 Service
 *
 * @author 김혁
 * @version 1.0
 * @since 2023-11.21
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

  private final PostRepository postRepository;
  private final DefRepository defRepository;
  private final TagRepository tagRepository;
  private final PostTagRepository postTagRepository;

  /**
   * 게시물 등록 ( 태그와 함께 )
   *
   * @param postRegDto
   * @param tags
   * @return postNo
   */
  @Transactional
  public int regPost(PostRegDto postRegDto, List<String> tags) {

    BoardDef boardDef =
        defRepository
            .findById(postRegDto.getBoardCd())
            .orElseThrow(() -> new IllegalArgumentException("해당하는 BoardDef가 없습니다."));

    Post post = postRepository.save(postRegDto.toEntity(boardDef));

    for (String tag : tags) {
      try {
        Tag t =
            tagRepository
                .findById(Integer.parseInt(tag))
                .orElseThrow(() -> new RuntimeException("해당하는 Tag가 없습니다."));
        PostTag postTag = PostTag.builder().post(post).boardDef(boardDef).tag(t).build();
        postTagRepository.save(postTag);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("게시물 등록중 숫자로만 된 태그가 와야합니다.");
      }
    }

    return post.getPostNo();
  }
}
