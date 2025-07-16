package com.example.hackathon6backend.domain.user.entity.repository;

import com.example.hackathon6backend.domain.user.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
