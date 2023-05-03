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

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping
    public ResponseEntity<List<StudentRank>> getRankings() {
        List<StudentRank> rankings = rankingService.rankStudents();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }
}
