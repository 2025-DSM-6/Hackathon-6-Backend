package com.example.hackathon6backend.domain.auth.service;

import com.example.hackathon6backend.domain.auth.presentation.dto.request.LoginRequest;
import com.example.hackathon6backend.domain.auth.presentation.dto.response.TokenResponse;
import com.example.hackathon6backend.domain.subject.entity.ClassRoom;
import com.example.hackathon6backend.domain.subject.exception.ClassRoomNotFoundException;
import com.example.hackathon6backend.domain.subject.repository.ClassRoomRepository;
import com.example.hackathon6backend.domain.user.dto.response.UserResponse;
import com.example.hackathon6backend.domain.user.entity.Student;
import com.example.hackathon6backend.domain.user.entity.Teacher;
import com.example.hackathon6backend.domain.user.entity.User;
import com.example.hackathon6backend.domain.user.entity.repository.StudentRepository;
import com.example.hackathon6backend.domain.user.entity.repository.TeacherRepository;
import com.example.hackathon6backend.domain.user.entity.repository.UserRepository;
import com.example.hackathon6backend.domain.user.exception.InvalidRoleException;
import com.example.hackathon6backend.domain.user.exception.PasswordMisMatchException;
import com.example.hackathon6backend.global.security.jwt.JwtProperties;
import com.example.hackathon6backend.global.security.jwt.JwtTokenProvider;
import com.example.hackathon6backend.global.xquare.XquareClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final XquareClient xquareClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;
    private final ClassRoomRepository classRoomRepository;

    @Transactional
    public TokenResponse execute(LoginRequest request) {
        User user = userRepository.findByAccountId(request.accountId())
            .orElse(null);

        if (user != null) {
            return generateToken(user);
        } else {
            UserResponse userResponse = xquareClient.user(request);

            User newUser = userRepository.findByAccountId(userResponse.accountId())
                .orElseGet(() -> {
                    if (userResponse.userRole().toString().equals("STU")) {
                        return createStudent(userResponse);
                    } else if (userResponse.userRole().toString().equals("SCH")) {
                        return createTeacher(userResponse);
                    } else {
                        throw InvalidRoleException.EXCEPTION;
                    }
                });

            checkPassword(userResponse, newUser);
            return generateToken(newUser);
        }
    }

    private void checkPassword(UserResponse userResponse, User user) {
        if (!passwordEncoder.matches(userResponse.password(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }
    }

    private TokenResponse generateToken(User user) {
        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId(), user.getRole());

        return TokenResponse.builder()
            .accessToken(accessToken)
            .accessExp(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
            .build();
    }

    private User createStudent(UserResponse userResponse) {
        if (!userResponse.userRole().toString().equals("STU")) {
            throw InvalidRoleException.EXCEPTION;
        }

        ClassRoom classRoom = classRoomRepository.findByClassNameAndGrade(userResponse.classNum(), userResponse.grade())
            .orElseThrow(() -> ClassRoomNotFoundException.EXCEPTION);

         User user = userRepository.save(
               User.builder()
                   .accountId(userResponse.accountId())
                   .password(passwordEncoder.encode(userResponse.password()))
                   .username(userResponse.name())
                   .role(userResponse.userRole())
               .build());

         studentRepository.save(
             Student.builder()
                 .user(user)
                 .grade(userResponse.grade())
                 .classNum(userResponse.classNum())
                 .num(userResponse.num())
                 .classEntity(classRoom)
                 .build()
         );

         return user;
    }

    private User createTeacher(UserResponse userResponse) {
        if (!userResponse.userRole().toString().equals("SCH")) {
            throw InvalidRoleException.EXCEPTION;
        }

        User user = userRepository.save(
            User.builder()
                .accountId(userResponse.accountId())
                .password(passwordEncoder.encode(userResponse.password()))
                .username(userResponse.name())
                .role(userResponse.userRole())
                .build()
        );

        teacherRepository.save(
            Teacher.builder()
                .user(user)
                .build()
        );

        return user;
    }

}
