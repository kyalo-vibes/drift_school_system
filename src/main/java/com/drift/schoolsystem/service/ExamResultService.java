package com.drift.schoolsystem.service;

import com.drift.schoolsystem.model.ExamResult;
import com.drift.schoolsystem.model.Student;
import com.drift.schoolsystem.model.Subject;
import com.drift.schoolsystem.repository.ExamResultRepository;
import com.drift.schoolsystem.repository.StudentRepository;
import com.drift.schoolsystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public ExamResult addExamResult(ExamResult examResult) {
        return examResultRepository.save(examResult);
    }

    public ExamResult updateExamResult(ExamResult updatedExamResult) {
        ExamResult existingExamResult = examResultRepository.findById(updatedExamResult.getId()).orElse(null);
        if (existingExamResult != null) {
            existingExamResult.setStudent(updatedExamResult.getStudent());
            existingExamResult.setSubject(updatedExamResult.getSubject());
            existingExamResult.setMarks(updatedExamResult.getMarks());
            examResultRepository.save(existingExamResult);
        }
        return existingExamResult;
    }

    public boolean deleteExamResult(Long id) {
        if (examResultRepository.existsById(id)) {
            examResultRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ExamResult> getAllExamResults() {
        return examResultRepository.findAll();
    }

    public ExamResult getExamResultById(Long id) {
        return examResultRepository.findById(id).orElse(null);
    }

    public List<ExamResult> getExamResultsBySubject(String subject) {
        return examResultRepository.findAll().stream()
                .filter(er -> er.getSubject().getName().equalsIgnoreCase(subject))
                .collect(Collectors.toList());
    }

    public List<ExamResult> getExamResultsByStudent(Long studentId) {
        return examResultRepository.findAll().stream()
                .filter(er -> er.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }

    public Map<String, Double> getAverageMarksBySubject() {
        List<ExamResult> examResults = examResultRepository.findAll();

        // Group ExamResults by subject
        Map<String, List<ExamResult>> examResultsBySubject = examResults.stream()
                .collect(Collectors.groupingBy(er -> er.getSubject().getName()));

        // Compute the average marks for each subject
        return examResultsBySubject.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToInt(ExamResult::getMarks)
                                .average()
                                .orElse(0.0)
                ));
    }

    public List<ExamResult> filterExamResultsByStudent(List<ExamResult> examResults, Student student) {
        return examResults.stream()
                .filter(er -> er.getStudent().getId().equals(student.getId()))
                .collect(Collectors.toList());
    }

    public List<ExamResult> filterExamResultsBySubject(List<ExamResult> examResults, Subject subject) {
        return examResults.stream()
                .filter(er -> er.getSubject().getId().equals(subject.getId()))
                .collect(Collectors.toList());
    }

    public double calculateAverageScore(List<ExamResult> examResults) {
        return examResults.stream()
                .mapToInt(ExamResult::getMarks)
                .average()
                .orElse(0.0);
    }
}
