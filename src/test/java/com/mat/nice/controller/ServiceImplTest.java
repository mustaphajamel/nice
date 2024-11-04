package com.mat.nice.controller;

import com.mat.nice.dto.PlayerRequestDto;
import com.mat.nice.dto.PlayerResponseDto;
import com.mat.nice.dto.TeamRequestDto;
import com.mat.nice.dto.TeamResponseDto;
import com.mat.nice.enumeration.Position;
import com.mat.nice.model.PlayerEntity;
import com.mat.nice.model.TeamEntity;
import com.mat.nice.repositrory.PlayerRepository;
import com.mat.nice.repositrory.TeamRepository;
import com.mat.nice.serviceImpl.PlayerServiceImpl;
import com.mat.nice.serviceImpl.TeamServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @InjectMocks
    private PlayerServiceImpl playerService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTeam() {
        // GIVEN
        PlayerRequestDto playerRequest = new PlayerRequestDto("mustapha", Position.CB);
        TeamRequestDto teamRequestDto = new TeamRequestDto("Club Africain", "CA", List.of(playerRequest), 2000.00);

        PlayerEntity playerEntity = new PlayerEntity("mustapha", Position.CB);
        when(playerRepository.findByNameAndPosition("mustapha", Position.CB))
                .thenReturn(Optional.of(playerEntity));
        when(playerRepository.save(any(PlayerEntity.class)))
                .thenReturn(playerEntity);

        // Mock TeamEntity to return from the team repository
        TeamEntity teamEntity = new TeamEntity("Club Africain", "CA", List.of(playerEntity), 2000.00);
        when(teamRepository.save(any(TeamEntity.class)))
                .thenReturn(teamEntity); // Ensures teamRepository.save returns a non-null entity

        // WHEN
        TeamResponseDto response = teamService.addTeam(teamRequestDto);

        // THEN
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Club Africain", response.getName());
        Assertions.assertEquals("CA", response.getAcronym());
        Assertions.assertEquals(2000.00, response.getBudget());
    }

    @Test
    public void testGetTeams() {
        // GIVEN
        Pageable pageable = PageRequest.of(0, 10);
        TeamEntity teamEntity = new TeamEntity("Club Africain", "CA", null, 2000.00);
        Page<TeamEntity> teamEntityPage = new PageImpl<>(List.of(teamEntity));

        when(teamRepository.findAll(pageable)).thenReturn(teamEntityPage);

        // WHEN
        Page<TeamResponseDto> responsePage = teamService.getTeams(pageable);

        // THEN
        Assertions.assertNotNull(responsePage);
        Assertions.assertEquals(1, responsePage.getTotalElements());
        Assertions.assertEquals("Club Africain", responsePage.getContent().get(0).getName());
        Assertions.assertEquals("CA", responsePage.getContent().get(0).getAcronym());
        Assertions.assertEquals(2000.00, responsePage.getContent().get(0).getBudget());

        verify(teamRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testAddPlayer() {
        // GIVEN
        PlayerRequestDto playerRequestDto = new PlayerRequestDto();
        playerRequestDto.setName("Pele");
        playerRequestDto.setPosition(Position.CB); // Assuming Position is an Enum

        PlayerEntity playerEntity = playerRequestDto.toPlayerEntity();
        playerEntity.setId(1L); // Simulate that the player has been saved and has an ID

        when(playerRepository.save(playerEntity)).thenReturn(playerEntity);

        // WHEN
        PlayerResponseDto responseDto = playerService.addPlayer(playerRequestDto);

        // THEN
        assertNotNull(responseDto);
        assertEquals("Pele", responseDto.getName());
        assertEquals(Position.CB, responseDto.getPosition());

    }

    @Test
    public void testGetPlayer_Success() {
        // GIVEN
        Long playerId = 1L;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(playerId);
        playerEntity.setName("Cristiano");
        playerEntity.setPosition(Position.CB); // Assuming Position is an Enum

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(playerEntity));

        // WHEN
        PlayerResponseDto responseDto = playerService.getPlayer(playerId);

        // THEN
        assertNotNull(responseDto);
        assertEquals("Cristiano", responseDto.getName());
        assertEquals(Position.CB, responseDto.getPosition());

    }

    @Test
    public void testGetPlayer_NotFound() {
        // GIVEN
        Long playerId = 1L;

        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        // WHEN-THEN
        Assertions.assertThrows(EntityNotFoundException.class, () -> playerService.getPlayer(playerId));
    }

}