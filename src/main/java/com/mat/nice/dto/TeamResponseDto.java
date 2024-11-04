package com.mat.nice.dto;

import com.mat.nice.model.PlayerEntity;
import com.mat.nice.model.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamResponseDto {

    String name;

    String acronym;

    List<PlayerEntity> playerEntities;

    Double budget;

}