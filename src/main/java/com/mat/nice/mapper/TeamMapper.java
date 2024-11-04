package com.mat.nice.mapper;

import com.mat.nice.dto.TeamResponseDto;
import com.mat.nice.model.TeamEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamResponseDto entityToDto(TeamEntity teamEntity);

    Page<TeamResponseDto> entitiesToDtos(Page<TeamEntity> teamEntities);

}