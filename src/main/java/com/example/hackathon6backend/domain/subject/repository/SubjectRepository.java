package com.example.hackathon6backend.domain.subject.repository;

import com.example.hackathon6backend.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
} 