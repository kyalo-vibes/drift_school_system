package com.drift.schoolsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentRank {

    private Student student;
    private List<ExamResult> examResults;
    private int totalMarks;
    private int totalPoints;
    private String finalGrade;

    public StudentRank() {}

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<ExamResult> getExamResults() {
        return examResults;
    }

    public void setExamResults(List<ExamResult> examResults) {
        this.examResults = examResults;
        this.totalMarks = calculateTotalMarks();
        this.totalPoints = calculateTotalPoints();
        this.finalGrade = calculateFinalGrade();
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

    private int getPoints(int marks) {
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

    private int calculateTotalMarks() {
        int compulsorySubjects = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Compulsory"))
                .mapToInt(ExamResult::getMarks)
                .sum();

        List<ExamResult> scienceResults = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        int topScienceSubjects = scienceResults.stream().mapToInt(ExamResult::getMarks).sum();

        List<ExamResult> otherResults = examResults.stream()
                .filter(er -> !er.getSubject().getCategory().equalsIgnoreCase("Compulsory") &&
                        !er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        int topOtherSubjects = otherResults.stream().mapToInt(ExamResult::getMarks).sum();

        return compulsorySubjects + topScienceSubjects + topOtherSubjects;
    }

    private int calculateTotalPoints() {
        int compulsorySubjectsPoints = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Compulsory"))
                .mapToInt(er -> getPoints(er.getMarks()))
                .sum();

        List<ExamResult> scienceResults = examResults.stream()
                .filter(er -> er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        int topScienceSubjectsPoints = scienceResults.stream().mapToInt(er -> getPoints(er.getMarks())).sum();

        List<ExamResult> otherResults = examResults.stream()
                .filter(er -> !er.getSubject().getCategory().equalsIgnoreCase("Compulsory") &&
                        !er.getSubject().getCategory().equalsIgnoreCase("Sciences"))
                .sorted(Comparator.comparingInt(ExamResult::getMarks).reversed())
                .limit(2)
                .collect(Collectors.toList());

        int topOtherSubjectsPoints = otherResults.stream().mapToInt(er -> getPoints(er.getMarks())).sum();

        return compulsorySubjectsPoints + topScienceSubjectsPoints + topOtherSubjectsPoints;
    }

    private String calculateFinalGrade() {
        double meanPoints = (double) getTotalPoints() / 7;

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
}
