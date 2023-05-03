package com.drift.schoolsystem.service;

import com.drift.schoolsystem.model.ExamResult;
import com.drift.schoolsystem.model.Student;
import com.drift.schoolsystem.model.StudentRank;
import com.drift.schoolsystem.repository.ExamResultRepository;
import com.drift.schoolsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
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

        List<StudentRank> rankedStudents = students.stream()
                .map(student -> {
                    List<ExamResult> studentExamResults = examResults.stream()
                            .filter(er -> er.getStudent().getId().equals(student.getId()))
                            .collect(Collectors.toList());
                    return calculateStudentRank(student, studentExamResults);
                })
                .sorted(Comparator.comparing(StudentRank::getTotalPoints).thenComparing(StudentRank::getTotalMarks).reversed())
                .collect(Collectors.toList());

        assignRanks(rankedStudents);
        generateCSV(rankedStudents);

        return rankedStudents;
    }

    private void assignRanks(List<StudentRank> rankedStudents) {
        int currentRank = 0;
        int previousPoints = -1;
        int sameRankCount = 0;

        for (StudentRank studentRank : rankedStudents) {
            if (studentRank.getTotalPoints() == previousPoints) {
                sameRankCount++;
            } else {
                currentRank += (sameRankCount + 1);
                sameRankCount = 0;
            }
            studentRank.setRank(currentRank);
            previousPoints = studentRank.getTotalPoints();
        }
    }


    private void generateCSV(List<StudentRank> rankedStudents) {
        String fileName = "student_rankings.csv";

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append("Name,Total Points,Total Marks,Rank\n");

            for (StudentRank studentRank : rankedStudents) {
                fileWriter.append(studentRank.getStudent().getFirstName() + " " + studentRank.getStudent().getLastName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(studentRank.getTotalPoints()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(studentRank.getTotalMarks()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(studentRank.getRank()));
                fileWriter.append("\n");
            }

            System.out.println("CSV file created: " + fileName);
        } catch (IOException e) {
            System.out.println("Error generating CSV file: " + e.getMessage());
        }
    }
}
