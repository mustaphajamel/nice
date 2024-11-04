package com.mat.nice.serviceImpl;

import com.mat.nice.dto.PlayerRequestDto;
import com.mat.nice.dto.PlayerResponseDto;
import com.mat.nice.model.PlayerEntity;
import com.mat.nice.repositrory.PlayerRepository;
import com.mat.nice.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public PlayerResponseDto addPlayer(PlayerRequestDto playerRequestDto) {
        return playerRepository.save(playerRequestDto.toPlayerEntity()).toPlayerResponseDto();
    }

    @Override
    public PlayerResponseDto getPlayer(Long id) {
        PlayerEntity playerEntity = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player with ID " + id + " not found"));

        return playerEntity.toPlayerResponseDto();
    }

}