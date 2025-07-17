package com.example.hackathon6backend.domain.student.service;

import com.example.hackathon6backend.domain.student.entity.Student;
import com.example.hackathon6backend.domain.student.exception.StudentNotFoundException;
import com.example.hackathon6backend.domain.student.presentation.response.GetStudentResponse;
import com.example.hackathon6backend.domain.student.repository.StudentRepository;
import com.example.hackathon6backend.domain.user.entity.User;
import com.example.hackathon6backend.domain.user.exception.UserNotFoundException;
import com.example.hackathon6backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetStudentService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<GetStudentResponse> execute(String userName) {
        List<User> users = userRepository.findAllByUsername(userName);

        if (users.isEmpty()) {
            throw UserNotFoundException.EXCEPTION;
        }

        return users.stream()
            .map(user -> {
                Student student = studentRepository.findById(user.getId())
                    .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
                return GetStudentResponse.builder()
                    .id(user.getId())
                    .userName(user.getUsername())
                    .grade(student.getGrade())
                    .classNum(student.getClassNum())
                    .num(student.getNum())
                    .build();
            })
            .toList();
    }
}
