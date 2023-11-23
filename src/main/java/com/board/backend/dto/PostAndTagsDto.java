package com.board.backend.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostAndTagsDto {
  private PostRegDto post;
  private List<String> tagNos;
}
