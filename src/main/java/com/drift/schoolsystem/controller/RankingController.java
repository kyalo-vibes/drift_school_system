package com.drift.schoolsystem.controller;

import com.drift.schoolsystem.model.StudentRank;
import com.drift.schoolsystem.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// RankingController handles HTTP requests related to student rankings.
@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    // RankingService handles the business logic for student rankings.
    @Autowired
    private RankingService rankingService;

    // getRankings retrieves the student rankings.
    @GetMapping
    public ResponseEntity<List<StudentRank>> getRankings() {
        List<StudentRank> rankings = rankingService.rankStudents();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }
}
