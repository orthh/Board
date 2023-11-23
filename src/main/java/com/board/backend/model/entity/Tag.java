package com.board.backend.model.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TAG")
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("태그ID")
  @Column(name = "TAG_NO")
  private int tagNo;

  @Column(name = "TAG")
  @Comment("태그")
  private String tag;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BOARD_CD")
  private BoardDef boardDef;

  @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
  private List<PostTag> postTags;

  @Builder
  public Tag(String tag, BoardDef boardDef) {
    this.tag = tag;
    this.boardDef = boardDef;
  }
}
