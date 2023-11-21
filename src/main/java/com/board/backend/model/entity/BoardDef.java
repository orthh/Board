package com.board.backend.model.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "BOARD_DEF")
public class BoardDef {

  @Id
  @Comment("게시판(분류)코드")
  @Column(name = "BOARD_CD", length = 20)
  String boardCd;

  @Comment("게시판(분류)이름")
  @Column(name = "BOARD_NM", length = 30, nullable = false)
  String boardNm;

  @OneToMany(mappedBy = "boardDef")
  private List<Post> posts;

  @OneToMany(mappedBy = "boardDef")
  private List<Tag> tags;

  @Builder
  public BoardDef(String boardCd, String boardNm) {
    this.boardCd = boardCd;
    this.boardNm = boardNm;
  }
}
