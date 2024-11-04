package com.mat.nice.serviceImpl;

import com.mat.nice.dto.PlayerRequestDto;
import com.mat.nice.dto.TeamRequestDto;
import com.mat.nice.dto.TeamResponseDto;
import com.mat.nice.model.PlayerEntity;
import com.mat.nice.model.TeamEntity;
import com.mat.nice.repositrory.PlayerRepository;
import com.mat.nice.repositrory.TeamRepository;
import com.mat.nice.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Transactional
    @Override
    public Page<TeamResponseDto> getTeams(Pageable pageable) {
        return teamRepository.findAll(pageable)
                .map(TeamEntity::toTeamResponseDto);
    }

    @Override
    public TeamResponseDto addTeam(TeamRequestDto teamRequestDto) {
        List<PlayerEntity> playerEntities = new ArrayList<>();
        if ( teamRequestDto.getPlayers()!=null && !teamRequestDto.getPlayers().isEmpty() ){

            for (PlayerRequestDto playerRequestDto : teamRequestDto.getPlayers()){
                Optional<PlayerEntity> byNameAndPosition = playerRepository.findByNameAndPosition(playerRequestDto.getName(), playerRequestDto.getPosition());
                if (byNameAndPosition.isEmpty()){
                    playerEntities.add(playerRepository.save(playerRequestDto.toPlayerEntity()));
                }
            }
        }
        TeamEntity teamEntity = teamRequestDto.toTeamEntity();
        teamEntity.setPlayerEntities(playerEntities);
        return teamRepository.save(teamEntity).toTeamResponseDto();
    }
}