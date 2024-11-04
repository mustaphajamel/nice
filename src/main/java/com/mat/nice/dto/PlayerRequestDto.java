package com.mat.nice.dto;

import com.mat.nice.enumeration.Position;
import com.mat.nice.model.PlayerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequestDto {

    String name;
    Position position;

    public PlayerEntity toPlayerEntity() {
        return new PlayerEntity(this.name, this.position);
    }

}