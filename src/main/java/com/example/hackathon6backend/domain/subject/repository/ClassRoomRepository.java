package com.example.hackathon6backend.domain.subject.repository;

import com.example.hackathon6backend.domain.subject.entity.ClassMajor;
import com.example.hackathon6backend.domain.subject.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findAllByClassMajorIn(List<ClassMajor> classMajors);
} 