package com.board.backend.dto;

import com.board.backend.model.entity.PostTag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagResDto {
  private int boardTagId;
  private String tag;

  public TagResDto(PostTag postTag) {
    this.boardTagId = postTag.getBoardTagId();
    this.tag = postTag.getTag().getTag();
  }
}
