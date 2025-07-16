package com.example.hackathon6backend.domain.room.repository;

import com.example.hackathon6backend.domain.room.entity.ClassMajor;
import com.example.hackathon6backend.domain.room.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findAllByClassMajorIn(List<ClassMajor> classMajors);
    Optional<ClassRoom> findByClassNameAndGrade(Integer className, Integer grade);
} 