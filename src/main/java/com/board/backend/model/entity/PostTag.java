package com.board.backend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "POST_TAG")
public class PostTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("게시물 태그ID")
  @Column(name = "BOARD_TAG_ID")
  private int boardTagId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "POST_NO")
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BOARD_CD")
  private BoardDef boardDef;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TAG_NO")
  private Tag tag;

  @Builder
  public PostTag(Post post, BoardDef boardDef, Tag tag){
    this.post = post;
    this.boardDef = boardDef;
    this.tag = tag;
  }
}
