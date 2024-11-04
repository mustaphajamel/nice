package com.mat.nice.model;

import com.mat.nice.dto.PlayerRequestDto;
import com.mat.nice.dto.TeamRequestDto;
import com.mat.nice.dto.TeamResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class TeamEntity extends  BaseEntity{


    String name;
    String acronym;

    @OneToMany
    List<PlayerEntity> playerEntities;

    Double budget;

    public TeamEntity(String name, String acronym, List<PlayerEntity> playerEntities, Double budget) {
        this.name = name;
        this.acronym = acronym;
        this.playerEntities = playerEntities;
        this.budget = budget;
    }

    public TeamRequestDto toTeamRequestDto() {
        List<PlayerRequestDto> playerRequestDtos = this.playerEntities.stream()
                .map(PlayerEntity::toPlayerRequestDto) // Assuming this method exists in PlayerEntity
                .collect(Collectors.toList());

        return new TeamRequestDto(this.name, this.acronym, playerRequestDtos, this.budget);
    }

    public TeamResponseDto toTeamResponseDto() {
        return new TeamResponseDto(this.name, this.acronym, this.playerEntities, this.budget);
    }

}