package com.example.hackathon6backend.domain.user.entity.repository;

import com.example.hackathon6backend.domain.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
