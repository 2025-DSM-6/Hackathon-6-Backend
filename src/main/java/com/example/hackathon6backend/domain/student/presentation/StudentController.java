package com.example.hackathon6backend.domain.student.presentation;

import com.example.hackathon6backend.domain.student.presentation.response.GetStudentResponse;
import com.example.hackathon6backend.domain.student.service.GetStudentListService;
import com.example.hackathon6backend.domain.student.service.GetStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final GetStudentListService getStudentListService;
    private final GetStudentService getStudentService;

    @GetMapping("/list")
    public List<GetStudentResponse> getStudentList() {
        return getStudentListService.execute();
    }

    @GetMapping
    public List<GetStudentResponse> getStudents(@RequestParam("name") String name) {
        return getStudentService.execute(name);
    }


}
