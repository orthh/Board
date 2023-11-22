package com.board.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.board.backend.model.entity.Post;

@Getter
@NoArgsConstructor
public class PostResDto {
  private String postNo;
  private String postSj;
  private String postCn;
  private String regstrId;

  public PostResDto(Post post){
    this.postNo = String.valueOf(post.getPostNo());
    this.postSj = post.getPostSj();
    this.postCn = post.getPostCn();
    this.regstrId = post.getRegstrId();
  }
}
