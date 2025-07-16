package com.example.hackathon6backend.domain.range.repository;

import com.example.hackathon6backend.domain.range.entity.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RangeRepository extends JpaRepository<Range, Long>, RangeRepositoryCustom {
    Optional<Range> findBySubjectId(Long subjectId);
    
    @Query("SELECT r FROM Range r JOIN FETCH r.subject s JOIN FETCH r.rangeContents WHERE s.user.userId = :teacherId")
    List<Range> findAllByTeacherId(@Param("teacherId") Long teacherId);
} 