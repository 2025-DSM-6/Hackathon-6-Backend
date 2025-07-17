package com.example.hackathon6backend.domain.teacher.repository;

import com.example.hackathon6backend.domain.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
