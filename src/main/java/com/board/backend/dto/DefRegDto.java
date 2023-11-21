package com.board.backend.dto;

import com.board.backend.model.entity.BoardDef;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DefRegDto {
  private String boardCd;
  private String boardNm;

  public BoardDef toEntity(){
    return BoardDef.builder()
      .boardCd(boardCd)
      .boardNm(boardNm)
      .build();
  }
}
