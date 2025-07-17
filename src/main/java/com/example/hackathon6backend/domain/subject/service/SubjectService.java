package com.example.hackathon6backend.domain.subject.service;

import com.example.hackathon6backend.domain.range.entity.ExamRangeClass;
import com.example.hackathon6backend.domain.room.exception.ClassRoomNotFoundException;
import com.example.hackathon6backend.domain.subject.dto.request.CreateSubjectByMajorRequest;
import com.example.hackathon6backend.domain.subject.dto.request.CreateSubjectRequest;
import com.example.hackathon6backend.domain.subject.dto.request.UpdateSubjectRequest;
import com.example.hackathon6backend.domain.subject.dto.response.SubjectResponse;
import com.example.hackathon6backend.domain.room.entity.ClassMajor;
import com.example.hackathon6backend.domain.room.entity.ClassRoom;
import com.example.hackathon6backend.domain.subject.entity.Subject;
import com.example.hackathon6backend.domain.room.repository.ClassRoomRepository;
import com.example.hackathon6backend.domain.subject.exception.SubjectNotFoundException;
import com.example.hackathon6backend.domain.subject.repository.SubjectRepository;
import com.example.hackathon6backend.domain.teacher.entity.Teacher;
import com.example.hackathon6backend.domain.teacher.repository.TeacherRepository;
import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClassRoomRepository classRoomRepository;

    @Transactional
    public void createSubject(Long userId, CreateSubjectRequest request) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        // 과목 생성
        final Subject subject = Subject.builder()
                .subjectName(request.getSubjectName())
                .user(teacher.getUser())
                .build();
        subjectRepository.save(subject);

        // 반 연결
        List<ClassRoom> classRooms = classRoomRepository.findAllById(request.getClassIds());
        if (classRooms.size() != request.getClassIds().size()) {
            throw ClassRoomNotFoundException.EXCEPTION;
        }

        // ExamRangeClass 생성 및 연결
        classRooms.forEach(classRoom -> {
            ExamRangeClass examRangeClass = ExamRangeClass.builder()
                    .classRoomEntity(classRoom)
                    .build();
            subject.addExamRangeClass(examRangeClass);
        });
    }

    public List<SubjectResponse> getMySubjects(Long userId) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        List<Subject> subjects = subjectRepository.findAllByUserId(teacher.getUser().getId());
        return subjects.stream()
                .map(SubjectResponse::of)
                .toList();
    }

    @Transactional
    public void updateSubject(Long subjectId, Long userId, UpdateSubjectRequest request) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> SubjectNotFoundException.EXCEPTION);

        // 권한 체크
        if (!subject.getUser().getId().equals(teacher.getUser().getId())) {
            throw new HackathonException(ErrorCode.FORBIDDEN);
        }

        // 과목 이름 수정
        subject.updateSubjectName(request.getSubjectName());

        // 기존 반 연결 제거
        subject.getExamRangeClasses().clear();

        // 새로운 반 연결
        List<ClassRoom> classRooms = classRoomRepository.findAllById(request.getClassIds());
        if (classRooms.size() != request.getClassIds().size()) {
            throw ClassRoomNotFoundException.EXCEPTION;
        }

        classRooms.forEach(classRoom -> {
            ExamRangeClass examRangeClass = ExamRangeClass.builder()
                    .classRoomEntity(classRoom)
                    .build();
            subject.addExamRangeClass(examRangeClass);
        });
    }

    @Transactional
    public void deleteSubject(Long subjectId, Long userId) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new HackathonException(ErrorCode.SUBJECT_NOT_FOUND));

        // 권한 체크
        if (!subject.getUser().getId().equals(teacher.getUser().getId())) {
            throw new HackathonException(ErrorCode.FORBIDDEN);
        }

        subjectRepository.delete(subject);
    }

    @Transactional
    public void createSubjectByMajor(Long userId, CreateSubjectByMajorRequest request) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        // 과목 생성
        final Subject subject = Subject.builder()
                .subjectName(request.getSubjectName())
                .user(teacher.getUser())
                .build();
        subjectRepository.save(subject);

        // 선택된 전공과 학년에 해당하는 모든 반 조회
        List<ClassRoom> classRooms = classRoomRepository.findAllByGradeAndClassMajorIn(
                request.getGrade(),
                request.getClassMajors()
        );
        
        if (classRooms.isEmpty()) {
            throw ClassRoomNotFoundException.EXCEPTION;
        }

        // ExamRangeClass 생성 및 연결
        classRooms.forEach(classRoom -> {
            ExamRangeClass examRangeClass = ExamRangeClass.builder()
                    .classRoomEntity(classRoom)
                    .build();
            subject.addExamRangeClass(examRangeClass);
        });
    }

    public List<SubjectResponse> getSubjectsByMajor(Long userId, List<ClassMajor> classMajors) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        List<Subject> subjects = subjectRepository.findAllByUserIdAndClassMajors(
                teacher.getUser().getId(),
                classMajors
        );

        return subjects.stream()
                .map(SubjectResponse::of)
                .toList();
    }
} 