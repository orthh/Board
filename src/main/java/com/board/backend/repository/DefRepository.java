package com.board.backend.repository;

import com.board.backend.model.entity.BoardDef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefRepository extends JpaRepository<BoardDef, String> {}
