package com.board.backend.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostAndTagsResDto {

  private String postNo;
  private String postSj;
  private String postCn;
  private String regstrId;
  private List<TagResDto> tags;

  @Builder
  public PostAndTagsResDto(
      int postNo, String postSj, String postCn, String regstrId, List<TagResDto> tags) {
    this.postNo = String.valueOf(postNo);
    this.postSj = postSj;
    this.postCn = postCn;
    this.regstrId = regstrId;
    this.tags = tags;
  }
}
