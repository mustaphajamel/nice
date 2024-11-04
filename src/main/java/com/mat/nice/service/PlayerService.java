package com.mat.nice.service;

import com.mat.nice.dto.PlayerRequestDto;
import com.mat.nice.dto.PlayerResponseDto;
import com.mat.nice.dto.TeamResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    PlayerResponseDto addPlayer(PlayerRequestDto playerRequestDto);

    PlayerResponseDto getPlayer(Long id);

}