package com.drift.schoolsystem.service;

import com.drift.schoolsystem.model.ExamResult;
import com.drift.schoolsystem.model.Student;
import com.drift.schoolsystem.model.StudentRank;
import com.drift.schoolsystem.repository.ExamResultRepository;
import com.drift.schoolsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private StudentRepository studentRepository;

    public StudentRank calculateStudentRank(Student student, List<ExamResult> examResults) {
        StudentRank studentRank = new StudentRank();
        studentRank.setStudent(student);
        studentRank.setExamResults(examResults);
        return studentRank;
    }

    public List<StudentRank> rankStudents() {
        List<Student> students = studentRepository.findAll();
        List<ExamResult> examResults = examResultRepository.findAll();

        return students.stream()
                .map(student -> {
                    List<ExamResult> studentExamResults = examResults.stream()
                            .filter(er -> er.getStudent().getId().equals(student.getId()))
                            .collect(Collectors.toList());
                    return calculateStudentRank(student, studentExamResults);
                })
                .sorted(Comparator.comparing(StudentRank::getTotalPoints).thenComparing(StudentRank::getTotalMarks).reversed())
                .collect(Collectors.toList());
    }
}
