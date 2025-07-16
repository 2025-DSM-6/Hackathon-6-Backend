package com.example.hackathon6backend.domain.range.repository;

import com.example.hackathon6backend.domain.range.entity.QRange;
import com.example.hackathon6backend.domain.range.entity.Range;
import com.example.hackathon6backend.domain.room.entity.QClassRoom;
import com.example.hackathon6backend.domain.subject.entity.QSubject;
import com.example.hackathon6backend.domain.range.entity.QExamRangeClass;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RangeRepositoryImpl implements RangeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Range> findAllByClassId(Long classId) {
        QRange range = QRange.range;
        QSubject subject = QSubject.subject;
        QExamRangeClass examRangeClass = QExamRangeClass.examRangeClass;
        QClassRoom classRoom = QClassRoom.classRoom;

        return queryFactory
                .selectDistinct(range)
                .from(range)
                .join(range.subject, subject)
                .join(examRangeClass)
                .on(examRangeClass.subject.eq(subject))
                .join(examRangeClass.classRoomEntity, classRoom)
                .where(classRoom.classId.eq(classId))
                .fetch();
    }
}