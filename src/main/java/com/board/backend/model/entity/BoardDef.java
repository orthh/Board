package com.board.backend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "BOARD_DEF")
public class BoardDef {

  @Id
  @Comment("게시판(분류)코드")
  @Column(name = "BOARD_CD", length = 20)
  String boardCodd;

  @Comment("게시판(분류)이름")
  @Column(name = "BOARD_NM", length = 30)
  String boardName;
}
