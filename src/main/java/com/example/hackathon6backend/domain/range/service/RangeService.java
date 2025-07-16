package com.example.hackathon6backend.domain.range.service;

import com.example.hackathon6backend.domain.range.dto.request.CreateRangeRequest;
import com.example.hackathon6backend.domain.range.dto.request.UpdateRangeRequest;
import com.example.hackathon6backend.domain.range.dto.response.RangeResponse;
import com.example.hackathon6backend.domain.range.entity.Range;
import com.example.hackathon6backend.domain.range.entity.RangeContent;
import com.example.hackathon6backend.domain.range.exception.RangeException;
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

import java.util.List;
import com.example.hackathon6backend.domain.range.dto.request.RangeContentRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RangeService {

    private final RangeRepository rangeRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public void createRange(Long subjectId, Long userId, CreateRangeRequest request) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new HackathonException(ErrorCode.SUBJECT_NOT_FOUND));

        if (!subject.getUser().getId().equals(teacher.getUser().getId())) {
            throw new RangeException(ErrorCode.INVALID_RANGE_ACCESS);
        }

        List<RangeContentRequest> examRangeList = request.getExamRange();
        if (examRangeList == null || examRangeList.isEmpty()) {
            throw new RangeException(ErrorCode.EMPTY_RANGE_CONTENT);
        }

        Range range = Range.builder()
                .subject(subject)
                .memo(request.getMemo())
                .build();

        Range savedRange = rangeRepository.save(range);

        examRangeList.forEach(content -> {
            RangeContent rangeContent = RangeContent.builder()
                    .examName(content.getExamName())
                    .examContent(content.getExamContent())
                    .build();
            savedRange.addRangeContent(rangeContent);
        });
    }

    public List<RangeResponse> getAllRanges(Long userId) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        List<Range> ranges = rangeRepository.findAllByTeacherId(teacher.getUser().getId());
        
        return ranges.stream()
                .map(RangeResponse::of)
                .toList();
    }

    @Transactional
    public void deleteRange(Long rangeId, Long userId) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        Range range = rangeRepository.findById(rangeId)
                .orElseThrow(() -> new RangeException(ErrorCode.RANGE_NOT_FOUND));

        if (!range.getSubject().getUser().getId().equals(teacher.getUser().getId())) {
            throw new RangeException(ErrorCode.INVALID_RANGE_ACCESS);
        }

        rangeRepository.delete(range);
    }

    @Transactional
    public void updateRange(Long rangeId, Long userId, UpdateRangeRequest request) {
        Teacher teacher = teacherRepository.findById(userId)
                .orElseThrow(() -> new HackathonException(ErrorCode.FORBIDDEN));

        Range range = rangeRepository.findById(rangeId)
                .orElseThrow(() -> new RangeException(ErrorCode.RANGE_NOT_FOUND));

        if (!range.getSubject().getUser().getId().equals(teacher.getUser().getId())) {
            throw new RangeException(ErrorCode.INVALID_RANGE_ACCESS);
        }

        List<RangeContentRequest> examRangeList = request.getExamRange();
        if (examRangeList == null || examRangeList.isEmpty()) {
            throw new RangeException(ErrorCode.EMPTY_RANGE_CONTENT);
        }

        range.updateRange(request.getMemo(), examRangeList);
    }
} 