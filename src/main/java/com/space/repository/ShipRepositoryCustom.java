package com.space.repository;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ShipRepositoryCustom {
    List<Ship> findByParams(
            String name,
            String planet,
            ShipType shipType,
            Long after,
            Long before,
            Boolean isUsed,
            Double minSpeed,
            Double maxSpeed,
            Integer minCrewSize,
            Integer maxCrewSize,
            Double minRating,
            Double maxRating
    );
    long countByParams(
            String name,
            String planet,
            ShipType shipType,
            Long after,
            Long before,
            Boolean isUsed,
            Double minSpeed,
            Double maxSpeed,
            Integer minCrewSize,
            Integer maxCrewSize,
            Double minRating,
            Double maxRating
    );
}
