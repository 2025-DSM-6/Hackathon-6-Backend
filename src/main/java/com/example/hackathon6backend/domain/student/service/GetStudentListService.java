package com.example.hackathon6backend.domain.student.service;

import com.example.hackathon6backend.domain.student.entity.Student;
import com.example.hackathon6backend.domain.student.presentation.response.GetStudentResponse;
import com.example.hackathon6backend.domain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetStudentListService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<GetStudentResponse> execute() {
        List<Student> students = studentRepository.findAll(
            Sort.by(Sort.Direction.ASC, "grade", "classNum", "num")
        );

        return students.stream()
            .map(student -> GetStudentResponse.builder()
                .id(student.getId())
                .userName(student.getUser().getUsername())
                .grade(student.getGrade())
                .classNum(student.getClassNum())
                .num(student.getNum())
                .build())
            .toList();
    }

}
