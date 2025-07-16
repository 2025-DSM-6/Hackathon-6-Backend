package com.example.hackathon6backend.domain.user.service;

import com.example.hackathon6backend.domain.user.dto.request.UpdateSubjectRequest;
import com.example.hackathon6backend.domain.user.entity.Student;
import com.example.hackathon6backend.domain.user.entity.User;
import com.example.hackathon6backend.domain.user.entity.repository.StudentRepository;
import com.example.hackathon6backend.domain.user.exception.UserNotFoundException;
import com.example.hackathon6backend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateSubjectService {

    private final UserFacade userFacade;
    private final StudentRepository studentRepository;

    @Transactional
    public void execute(UpdateSubjectRequest request) {
        User user = userFacade.getUser();
        Student student = studentRepository.findById(user.getId())
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        student.updateSubject(request.electiveSubject());
    }
}
