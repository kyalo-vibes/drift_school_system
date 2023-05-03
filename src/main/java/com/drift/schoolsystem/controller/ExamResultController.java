package com.drift.schoolsystem.controller;

import com.drift.schoolsystem.model.ExamResult;
import com.drift.schoolsystem.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// ExamResultController handles HTTP requests related to exam results.
@RestController
@RequestMapping("/api/exam-results")
public class ExamResultController {

    // ExamResultService handles the business logic for exam results.
    @Autowired
    private ExamResultService examResultService;

    // addExamResult creates a new exam result.
    @PostMapping
    public ExamResult addExamResult(@RequestBody ExamResult examResult) {
        return examResultService.addExamResult(examResult);
    }

    // updateExamResult updates an existing exam result.
    @PutMapping
    public ExamResult updateExamResult(@RequestBody ExamResult examResult) {
        return examResultService.updateExamResult(examResult);
    }

    // deleteExamResult deletes an exam result by ID.
    @DeleteMapping("/{id}")
    public boolean deleteExamResult(@PathVariable Long id) {
        return examResultService.deleteExamResult(id);
    }

    // getAllExamResults retrieves all exam results.
    @GetMapping
    public List<ExamResult> getAllExamResults() {
        return examResultService.getAllExamResults();
    }

    // getExamResultById retrieves an exam result by ID.
    @GetMapping("/{id}")
    public ExamResult getExamResultById(@PathVariable Long id) {
        return examResultService.getExamResultById(id);
    }

    // getExamResultsBySubject retrieves exam results filtered by subject.
    @GetMapping("/by-subject/{subject}")
    public List<ExamResult> getExamResultsBySubject(@PathVariable String subject) {
        return examResultService.getExamResultsBySubject(subject);
    }

    // getExamResultsByStudent retrieves exam results filtered by student ID.
    @GetMapping("/by-student/{studentId}")
    public List<ExamResult> getExamResultsByStudent(@PathVariable Long studentId) {
        return examResultService.getExamResultsByStudent(studentId);
    }

    // getAverageMarksBySubject retrieves the average marks for each subject.
    @GetMapping("/average-marks")
    public Map<String, Double> getAverageMarksBySubject() {
        return examResultService.getAverageMarksBySubject();
    }
}
