package com.board.backend.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 엔터티들의 기본적인 필드를 정의하는 베이스 엔터티 클래스
 *
 * @author 김혁
 * @version 1.0
 * @since 2023.11.21
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  /** 생성된 시간입니다. 이 필드는 처음 저장될 때 자동으로 현재 시간이 설정되며, 이후에는 변경되지 않습니다. */
  @CreatedDate
  @Comment("작성일시 ")
  @Column(name = "REG_DT", updatable = false)
  private LocalDateTime registrationDateTime;
}
