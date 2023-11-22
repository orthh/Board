package com.board.backend.model.entity;

import java.time.LocalDateTime;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "POST")
public class Post extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Comment("글번호")
  @Column(name = "POST_NO")
  private int postNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BOARD_CD")
  private BoardDef boardDef;

  @Column(name = "POST_SJ")
  @Comment("제목")
  private String postSj;

  @Column(name = "POST_CN")
  @Comment("내용")
  private String postCn;

  @Column(name = "REGSTR_ID")
  @Comment("작성자ID")
  private String regstrId;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<PostTag> postTags;

  @Builder
  public Post(
      BoardDef boardDef, String postSj, String postCn, String regstrId, List<PostTag> postTags) {
    this.boardDef = boardDef;
    this.postSj = postSj;
    this.postCn = postCn;
    this.regstrId = regstrId;
    this.postTags = postTags;
  }
}
