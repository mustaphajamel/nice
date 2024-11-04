package com.mat.nice.model;

import com.mat.nice.dto.PlayerRequestDto;
import com.mat.nice.dto.PlayerResponseDto;
import com.mat.nice.dto.TeamRequestDto;
import com.mat.nice.dto.TeamResponseDto;
import com.mat.nice.enumeration.Position;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity extends  BaseEntity{

    String name;
    Position position;


    public PlayerRequestDto toPlayerRequestDto() {
        return new PlayerRequestDto(this.name, this.position);
    }

    public PlayerResponseDto toPlayerResponseDto() {
        return new PlayerResponseDto(this.name, this.position);
    }


}