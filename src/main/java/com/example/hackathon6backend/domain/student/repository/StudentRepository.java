package com.example.hackathon6backend.domain.student.repository;

import com.example.hackathon6backend.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
