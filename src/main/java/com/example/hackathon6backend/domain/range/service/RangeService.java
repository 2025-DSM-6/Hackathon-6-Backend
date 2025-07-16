package com.example.hackathon6backend.domain.range.service;

import com.example.hackathon6backend.domain.range.dto.request.CreateRangeRequest;
import com.example.hackathon6backend.domain.range.entity.Range;
import com.example.hackathon6backend.domain.range.entity.RangeContent;
import com.example.hackathon6backend.domain.range.repository.RangeContentRepository;
import com.example.hackathon6backend.domain.range.repository.RangeRepository;
import com.example.hackathon6backend.domain.subject.entity.Subject;
import com.example.hackathon6backend.domain.subject.repository.SubjectRepository;
import com.example.hackathon6backend.domain.user.entity.Teacher;
import com.example.hackathon6backend.domain.user.repository.TeacherRepository;
import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RangeService {

    private final RangeRepository rangeRepository;
    private final RangeContentRepository rangeContentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public void createRange(Long subjectId, Long userId, CreateRangeRequest request) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new HackathonException(ErrorCode.SUBJECT_NOT_FOUND));

        if (!subject.getUser().getUserId().equals(teacher.getUser().getUserId())) {
            throw new HackathonException(ErrorCode.FORBIDDEN);
        }

        Range range = Range.builder()
                .subject(subject)
                .memo(request.getMemo())
                .build();

        Range savedRange = rangeRepository.save(range);

        request.getExamRange().forEach(content -> {
            RangeContent rangeContent = RangeContent.builder()
                    .examRange(savedRange)
                    .examContent(content)
                    .build();
            rangeContentRepository.save(rangeContent);
        });
    }
} 