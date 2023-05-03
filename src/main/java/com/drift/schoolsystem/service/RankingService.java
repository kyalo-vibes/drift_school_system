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

// RankingService provides methods for ranking students based on their exam results.
@Service
public class RankingService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Calculates the rank of a student based on their exam results.
    public StudentRank calculateStudentRank(Student student, List<ExamResult> examResults) {
        StudentRank studentRank = new StudentRank();
        studentRank.setStudent(student);
        studentRank.setExamResults(examResults);
        return studentRank;
    }

    // Ranks students based on their total points and total marks in descending order.
    public List<StudentRank> rankStudents() {
        // Retrieve all students and exam results from the respective repositories.
        List<Student> students = studentRepository.findAll();
        List<ExamResult> examResults = examResultRepository.findAll();

        // Calculate student ranks using total points and total marks.
        List<StudentRank> rankedStudents = students.stream()
                .map(student -> {
                    // Filter exam results for each student using their ID.
                    List<ExamResult> studentExamResults = examResults.stream()
                            .filter(er -> er.getStudent().getId().equals(student.getId()))
                            .collect(Collectors.toList());
                    // Calculate the student rank for each student.
                    return calculateStudentRank(student, studentExamResults);
                })
                // Sort students by total points and then by total marks in descending order.
                .sorted(Comparator.comparing(StudentRank::getTotalPoints).thenComparing(StudentRank::getTotalMarks).reversed())
                .collect(Collectors.toList());
        // Assign ranks to the sorted students and generate CSV file
        assignRanks(rankedStudents);
        generateCSV(rankedStudents);

        return rankedStudents;
    }

    // Assigns ranks to the students based on their total points in descending order.
    // Students with the same total points receive the same rank.
    private void assignRanks(List<StudentRank> rankedStudents) {
        int currentRank = 0;    // Tracks the current rank.
        int previousPoints = -1;    // Tracks the points of the previous student.
        int sameRankCount = 0;  // Counts the number of students with the same rank.

        for (StudentRank studentRank : rankedStudents) {
            // Check if the current student has the same total points as the previous student.
            if (studentRank.getTotalPoints() == previousPoints) {
                // If the points are the same, increment the sameRankCount.
                sameRankCount++;
            } else {
                // If the points are different, update the current rank and reset sameRankCount.
                currentRank += (sameRankCount + 1);
                sameRankCount = 0;
            }
            // Set the rank for the current student. and update previousPoints for next iteration
            studentRank.setRank(currentRank);
            previousPoints = studentRank.getTotalPoints();
        }
    }

    // Generates a CSV file containing student rankings.
    private void generateCSV(List<StudentRank> rankedStudents) {
        String fileName = "rank_sheet.csv";

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append("Name,Total Marks,Total Points, Grade, Rank\n");

            for (StudentRank studentRank : rankedStudents) {
                fileWriter.append(studentRank.getStudent().getFirstName() + " " + studentRank.getStudent().getLastName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(studentRank.getTotalMarks()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(studentRank.getTotalPoints()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(studentRank.getFinalGrade()));
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
