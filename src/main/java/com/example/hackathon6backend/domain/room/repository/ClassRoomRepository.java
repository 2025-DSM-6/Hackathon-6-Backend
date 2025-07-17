package com.example.hackathon6backend.domain.room.repository;

import com.example.hackathon6backend.domain.room.entity.ClassMajor;
import com.example.hackathon6backend.domain.room.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findAllByClassMajorIn(List<ClassMajor> classMajors);
    Optional<ClassRoom> findByClassNameAndGrade(Integer className, Integer grade);
    
    @Query("SELECT c FROM ClassRoom c WHERE c.classMajor IN :majors AND (:grade IS NULL OR c.grade = :grade)")
    List<ClassRoom> findAllByGradeAndClassMajorIn(
            @Param("grade") Integer grade,
            @Param("majors") List<ClassMajor> majors
    );
} 