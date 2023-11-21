package com.board.backend.dto;

import com.board.backend.model.entity.BoardDef;
import com.board.backend.model.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagRegDto {

  private String tag;
  private String boardCd;

  public Tag toEntity(BoardDef boardDef) {
    return Tag.builder().tag(tag).boardDef(boardDef).build();
  }
}
