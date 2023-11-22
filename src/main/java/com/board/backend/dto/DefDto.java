package com.board.backend.dto;

import com.board.backend.model.entity.BoardDef;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DefDto {
  private String boardCd;
  private String boardNm;

  public BoardDef toEntity(){
    return BoardDef.builder()
      .boardCd(boardCd)
      .boardNm(boardNm)
      .build();
  }

  public DefDto(BoardDef boardDef){
    this.boardCd = boardDef.getBoardCd();
    this.boardNm = boardDef.getBoardNm();
  }
}
