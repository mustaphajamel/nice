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

import org.slf4j.*;


@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public PlayerResponseDto addPlayer(PlayerRequestDto playerRequestDto) {
        return playerRepository.save(playerRequestDto.toPlayerEntity()).toPlayerResponseDto();
    }

    @Override
    public PlayerResponseDto getPlayer(Long id) {
        logger.info("PlayerServiceImpl::getPlayer : getPlayer details by ID");

        PlayerEntity playerEntity = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player with ID " + id + " not found"));

        return playerEntity.toPlayerResponseDto();
    }

}