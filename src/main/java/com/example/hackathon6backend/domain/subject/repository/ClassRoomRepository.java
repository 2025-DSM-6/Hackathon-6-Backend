package com.example.hackathon6backend.domain.subject.repository;

import com.example.hackathon6backend.domain.subject.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
} 