package com.board.backend.service;

import com.board.backend.dto.PostAndTagsResDto;
import com.board.backend.dto.PostRegDto;
import com.board.backend.dto.PostResDto;
import com.board.backend.dto.TagResDto;
import com.board.backend.model.entity.BoardDef;
import com.board.backend.model.entity.Post;
import com.board.backend.model.entity.PostTag;
import com.board.backend.model.entity.Tag;
import com.board.backend.repository.DefRepository;
import com.board.backend.repository.PostRepository;
import com.board.backend.repository.PostTagRepository;
import com.board.backend.repository.TagRepository;
import java.util.ArrayList;
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

    List<PostTag> postTags = new ArrayList<>();
    for (String tagNo : tags) {
      try {
        Tag t =
            tagRepository
                .findById(Integer.parseInt(tagNo))
                .orElseThrow(() -> new RuntimeException("해당하는 Tag가 없습니다."));
        PostTag postTag = PostTag.builder().post(post).boardDef(boardDef).tag(t).build();
        postTags.add(postTag);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("게시물 등록중 숫자로만 된 태그ID가 와야합니다. tagNo = " + tagNo);
      }
    }
    postTagRepository.saveAll(postTags);

    return post.getPostNo();
  }

  /**
   * 게시물 수정
   *
   * @param postNo
   * @param postRegDto
   * @return postNo
   */
  @Transactional
  public int updatePost(int postNo, PostRegDto postRegDto) {
    Post post =
        postRepository
            .findById(postNo)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 Post가 없습니다."));
    post.setPostCn(postRegDto.getPostCn());
    post.setPostSj(postRegDto.getPostSj());
    post.setRegstrId(postRegDto.getRegstrId());
    return postNo;
  }

  /**
   * 태그에 해당하는 게시물 조회 ( 태그 배열과 함께 조회 )
   *
   * @param tagNo
   * @return List<PostResDto>
   */
  public List<PostAndTagsResDto> getPostsByTagNo(int tagNo) {
    Tag tag =
        tagRepository
            .findById(tagNo)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 Tag가 없습니다."));
    List<PostTag> postTags = postTagRepository.findAllByTag(tag);
    // List<PostResDto> postResDtos = new ArrayList<>();
    List<PostAndTagsResDto> response = new ArrayList<>();

    for (PostTag postTag : postTags) {
      // postResDtos.add(new PostResDto(postTag.getPost()));
      Post post = postTag.getPost();
      List<PostTag> newPostTags = post.getPostTags();
      PostAndTagsResDto postAndTagsResDto =
          PostAndTagsResDto.builder()
              .postNo(post.getPostNo())
              .postSj(post.getPostSj())
              .postCn(post.getPostCn())
              .regstrId(post.getRegstrId())
              .build();
      List<TagResDto> tagResDtos = new ArrayList<>();
      for (PostTag newPostTag : newPostTags) {
        TagResDto tagResDto = new TagResDto(newPostTag.getTag());
        tagResDtos.add(tagResDto);
      }
      postAndTagsResDto.setTags(tagResDtos);
      response.add(postAndTagsResDto);
    }

    return response;
  }

  /**
   * 게시물 삭제 ( Cascade - 연관된 게시물 태그도 함께 삭제 )
   *
   * @param postNo
   * @return postNo
   */
  @Transactional
  public Integer deletePost(String postNo) {
    postRepository.deleteById(Integer.parseInt(postNo));
    return Integer.parseInt(postNo);
  }
}
