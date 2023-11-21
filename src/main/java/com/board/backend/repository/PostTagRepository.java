package com.board.backend.repository;

import com.board.backend.model.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {}
