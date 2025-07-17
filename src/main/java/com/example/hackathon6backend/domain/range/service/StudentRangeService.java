package com.example.hackathon6backend.domain.range.service;

import com.example.hackathon6backend.domain.range.dto.response.StudentRangeResponse;
import com.example.hackathon6backend.domain.range.entity.Range;
import com.example.hackathon6backend.domain.range.repository.RangeRepository;
import com.example.hackathon6backend.domain.room.entity.ClassRoom;
import com.example.hackathon6backend.domain.room.exception.ClassRoomNotFoundException;
import com.example.hackathon6backend.domain.room.repository.ClassRoomRepository;
import com.example.hackathon6backend.domain.student.entity.Student;
import com.example.hackathon6backend.domain.student.repository.StudentRepository;
import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentRangeService {

    private final RangeRepository rangeRepository;
    private final StudentRepository studentRepository;
    private final ClassRoomRepository classRoomRepository;

    public List<StudentRangeResponse> getMyRanges(Long userId) {
        Student student = studentRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        ClassRoom classRoom = student.getClassRoomEntity();
        if (classRoom == null) {
            throw ClassRoomNotFoundException.EXCEPTION;
        }

        // 학생의 반에 해당하는 모든 시험범위 조회
        List<Range> ranges = rangeRepository.findAllByClassId(classRoom.getClassId());

        return ranges.stream()
                .map(StudentRangeResponse::of)
                .toList();
    }
} 