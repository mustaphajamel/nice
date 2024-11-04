package com.mat.nice.tests;

import com.mat.nice.dto.TeamRequestDto;
import com.mat.nice.dto.TeamResponseDto;
import com.mat.nice.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;


    @GetMapping
    public ResponseEntity<Page<TeamResponseDto>> getAllTeams(Pageable pageable) {
        return ResponseEntity.ok(teamService.getTeams(pageable));
    }

    @PostMapping("/addTeam")
    public ResponseEntity<TeamResponseDto> saveTeam(@RequestBody TeamRequestDto teamRequestDto) {
        return ResponseEntity.ok(teamService.addTeam(teamRequestDto));
    }

}