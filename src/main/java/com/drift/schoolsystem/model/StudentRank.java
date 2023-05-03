package com.drift.schoolsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// StudentRank represents the ranking of a student based on their exam results.
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentRank {

    private Student student;
    private List<ExamResult> examResults;
    private int totalMarks;
    private int totalPoints;
    private String finalGrade;
    private int rank;

    public StudentRank() {}

    // Getters and setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<ExamResult> getExamResults() {
        return examResults;
    }

    // Automatically calculates total marks, total points, and final grade.
    public void setExamResults(List<ExamResult> examResults) {
        this.examResults = examResults;
        this.totalMarks = calculateTotalMarks();
        this.totalPoints = calculateTotalPoints();
        this.finalGrade = calculateFinalGrade();
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public int getTotalMarks() {
        return totalMarks;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    // Determines the points awarded for a given mark using the requirements given.

    private int getPoints(int marks) {
        // Points are assigned based on the given marks.
        if (marks >= 95) return 12;
        if (marks >= 89) return 11;
        if (marks >= 83) return 10;
        if (marks >= 76) return 9;
        if (marks >= 69) return 8;
        if (marks >= 61) return 7;
        if (marks >= 53) return 6;
        if (marks >= 44) return 5;
        if (marks >= 36) return 4;
        if (marks >= 28) return 3;
        if (marks >= 21) return 2;
        return 1;
    }

    // Calculates the total marks for a student by combining compulsory subjects,
    // top 2 science subjects, and top 2 other subjects.
    private int calculateTotalMarks() {
        // Calculate the sum of marks for compulsory subjects.
        int compulsorySubjects = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Compulsory"))
                .mapToInt(ExamResult::getMarks)
                .sum();

        // Find the top 2 science subjects by marks.
        List<ExamResult> scienceResults = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        // Calculate the sum of marks for the top 2 science subjects.
        int topScienceSubjects = scienceResults.stream().mapToInt(ExamResult::getMarks).sum();

        // Find the top 2 other subjects by marks.
        List<ExamResult> otherResults = examResults.stream()
                .filter(er -> !er.getSubject().getCategory().equalsIgnoreCase("Compulsory") &&
                        !er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        // Calculate the sum of marks for the top 2 other subjects.
        int topOtherSubjects = otherResults.stream().mapToInt(ExamResult::getMarks).sum();

        // Return the total marks by adding the marks from compulsory, top science, and top other subjects.
        return compulsorySubjects + topScienceSubjects + topOtherSubjects;
    }

    // Calculates the total points for a student by combining points from compulsory subjects,
    // top 2 science subjects, and top 2 other subjects.
    private int calculateTotalPoints() {
        // Calculate the sum of points for compulsory subjects.
        int compulsorySubjectsPoints = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Compulsory"))
                .mapToInt(er -> getPoints(er.getMarks()))
                .sum();

        // Find the top 2 science subjects by marks.
        List<ExamResult> scienceResults = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        // Calculate the sum of points for the top 2 science subjects.
        int topScienceSubjectsPoints = scienceResults.stream().mapToInt(er -> getPoints(er.getMarks())).sum();

        // Find the top 2 other subjects by marks.
        List<ExamResult> otherResults = examResults.stream()
                .filter(er -> !er.getSubject().getCategory().equalsIgnoreCase("Compulsory") &&
                        !er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        // Calculate the sum of points for the top 2 other subjects.
        int topOtherSubjectsPoints = otherResults.stream().mapToInt(er -> getPoints(er.getMarks())).sum();

        // Return the total points by adding the points from compulsory, top science, and top other subjects.
        return compulsorySubjectsPoints + topScienceSubjectsPoints + topOtherSubjectsPoints;
    }

    // Determines the final grade of a student based on their mean points.
    private String calculateFinalGrade() {
        // Calculate the mean points by dividing the total points by 7.
        double meanPoints = (double) getTotalPoints() / 7;

        // Assign the final grade based on the calculated mean points.
        if (meanPoints >= 11.5) return "A";
        if (meanPoints >= 10.5) return "A-";
        if (meanPoints >= 9.5) return "B+";
        if (meanPoints >= 8.5) return "B";
        if (meanPoints >= 7.5) return "B-";
        if (meanPoints >= 6.5) return "C+";
        if (meanPoints >= 5.5) return "C";
        if (meanPoints >= 4.5) return "C-";
        if (meanPoints >= 3.5) return "D+";
        if (meanPoints >= 2.5) return "D";
        if (meanPoints >= 1.5) return "D-";
        return "E";
    }

    public String getName() {
        return student.getFirstName() + " " + student.getLastName();
    }
}
