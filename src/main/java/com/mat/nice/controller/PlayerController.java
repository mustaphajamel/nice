package com.mat.nice.controller;

import com.mat.nice.dto.PlayerResponseDto;
import com.mat.nice.service.PlayerService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayer(@NonNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

}