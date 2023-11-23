package com.board.backend.dto;

import com.board.backend.model.entity.PostTag;
import com.board.backend.model.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagResDto {
  private String tagNo;
  private String tag;

  public TagResDto(Tag tag) {
    this.tagNo = String.valueOf(tag.getTagNo());
    this.tag = tag.getTag();
  }
}
