package com.example.hackathon6backend.domain.user.service;

import com.example.hackathon6backend.domain.student.presentation.response.GetMyInformResponse;
import com.example.hackathon6backend.domain.student.entity.Student;
import com.example.hackathon6backend.domain.user.entity.User;
import com.example.hackathon6backend.domain.student.repository.StudentRepository;
import com.example.hackathon6backend.domain.user.exception.UserNotFoundException;
import com.example.hackathon6backend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUserService {

    private final UserFacade userFacade;
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public GetMyInformResponse execute() {
        User user = userFacade.getUser();
        Student student = studentRepository.findById(user.getId())
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return GetMyInformResponse.builder()
            .id(user.getId())
            .userName(user.getUsername())
            .grade(student.getGrade())
            .classNum(student.getClassNum())
            .num(student.getNum())
            .electiveSubject(student.getElectiveSubject())
            .build();
    }
}
