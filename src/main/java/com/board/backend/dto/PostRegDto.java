package com.board.backend.dto;

import com.board.backend.model.entity.BoardDef;
import com.board.backend.model.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRegDto {

  String boardCd;
  String postSj;
  String postCn;
  String regstrId;

  public Post toEntity(BoardDef boardDef) {
    return Post.builder()
        .boardDef(boardDef)
        .postSj(this.postSj)
        .postCn(this.postCn)
        .regstrId(this.regstrId)
        .build();
  }
}
