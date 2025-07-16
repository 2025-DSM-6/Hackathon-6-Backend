package com.example.hackathon6backend.domain.range.repository;

import com.example.hackathon6backend.domain.range.entity.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RangeRepository extends JpaRepository<Range, Long>, RangeRepositoryCustom {
    @Query("SELECT r FROM Range r WHERE r.subject.subjectId = :subjectId")
    Optional<Range> findBySubjectId(@Param("subjectId") Long subjectId);
    
    @Query("SELECT DISTINCT r FROM Range r JOIN FETCH r.subject s LEFT JOIN FETCH r.rangeContents WHERE s.user.id = :teacherId")
    List<Range> findAllByTeacherId(@Param("teacherId") Long teacherId);
} 