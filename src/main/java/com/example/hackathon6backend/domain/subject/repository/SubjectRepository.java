package com.example.hackathon6backend.domain.subject.repository;

import com.example.hackathon6backend.domain.subject.entity.ClassMajor;
import com.example.hackathon6backend.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s LEFT JOIN FETCH s.examRangeClasses WHERE s.user.id = :userId")
    List<Subject> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT DISTINCT s FROM Subject s " +
           "JOIN s.examRangeClasses erc " +
           "JOIN erc.classRoomEntity c " +
           "WHERE s.user.id = :userId " +
           "AND c.classMajor IN :classMajors")
    List<Subject> findAllByUserIdAndClassMajors(
            @Param("userId") Long userId,
            @Param("classMajors") List<ClassMajor> classMajors
    );
} 