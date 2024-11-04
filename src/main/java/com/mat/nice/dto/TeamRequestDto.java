package com.mat.nice.dto;

import com.mat.nice.model.PlayerEntity;
import com.mat.nice.model.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {

    String name;

    String acronym;

    List<PlayerRequestDto> players;

    Double budget;

    public TeamEntity toTeamEntity() {
        List<PlayerEntity> playerEntities = (this.players == null)
                ? null
                : this.players.stream()
                .map(PlayerRequestDto::toPlayerEntity)
                .toList();

        return new TeamEntity(name, acronym, playerEntities, budget);
    }


}