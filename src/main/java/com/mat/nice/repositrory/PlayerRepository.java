package com.mat.nice.repositrory;

import com.mat.nice.enumeration.Position;
import com.mat.nice.model.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>  {

    Optional<PlayerEntity> findByNameAndPosition(String name, Position position);

}