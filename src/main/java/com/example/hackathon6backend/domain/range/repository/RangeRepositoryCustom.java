package com.example.hackathon6backend.domain.range.repository;

import com.example.hackathon6backend.domain.range.entity.Range;

import java.util.List;

public interface RangeRepositoryCustom {
    List<Range> findAllByClassId(Long classId);
} 