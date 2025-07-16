package com.example.hackathon6backend.domain.range.repository;

import com.example.hackathon6backend.domain.range.entity.Range;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RangeRepository extends JpaRepository<Range, Long> {
} 