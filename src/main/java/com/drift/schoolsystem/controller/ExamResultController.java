package com.drift.schoolsystem.controller;

import com.drift.schoolsystem.model.ExamResult;
import com.drift.schoolsystem.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/exam-results")
public class ExamResultController {

    @Autowired
    private ExamResultService examResultService;

    @PostMapping
    public ExamResult addExamResult(@RequestBody ExamResult examResult) {
        return examResultService.addExamResult(examResult);
    }

    @PutMapping
    public ExamResult updateExamResult(@RequestBody ExamResult examResult) {
        return examResultService.updateExamResult(examResult);
    }

    @DeleteMapping("/{id}")
    public boolean deleteExamResult(@PathVariable Long id) {
        return examResultService.deleteExamResult(id);
    }

    @GetMapping
    public List<ExamResult> getAllExamResults() {
        return examResultService.getAllExamResults();
    }

    @GetMapping("/{id}")
    public ExamResult getExamResultById(@PathVariable Long id) {
        return examResultService.getExamResultById(id);
    }

    @GetMapping("/by-subject/{subject}")
    public List<ExamResult> getExamResultsBySubject(@PathVariable String subject) {
        return examResultService.getExamResultsBySubject(subject);
    }

    @GetMapping("/by-student/{studentId}")
    public List<ExamResult> getExamResultsByStudent(@PathVariable Long studentId) {
        return examResultService.getExamResultsByStudent(studentId);
    }

    @GetMapping("/average-marks")
    public Map<String, Double> getAverageMarksBySubject() {
        return examResultService.getAverageMarksBySubject();
    }
}
