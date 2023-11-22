package com.board.backend.repository;

import com.board.backend.model.entity.PostTag;
import com.board.backend.model.entity.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
  public List<PostTag> findAllByTag(Tag tag);
}
