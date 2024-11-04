package com.mat.nice.service;

import com.mat.nice.dto.TeamRequestDto;
import com.mat.nice.dto.TeamResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {


    Page<TeamResponseDto> getTeams(Pageable pageable);
    TeamResponseDto addTeam(TeamRequestDto teamRequestDto);

}