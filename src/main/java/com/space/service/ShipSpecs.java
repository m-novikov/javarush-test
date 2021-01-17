package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;

public class ShipSpecs {
    public static Specification<Ship> byName(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        });
    }

    public static Specification<Ship> byPlanet(String planet) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("planet"), "%" + planet + "%");
        });
    }

    public static Specification<Ship> afterProdDate(Long after) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), new Timestamp(after));
        });
    }

    public static Specification<Ship> beforeProdDate(Long before) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), new Timestamp(before));
        });
    }

    public static Specification<Ship> byShipType(ShipType shipType) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("shipType"), shipType);
        });
    }

    public static Specification<Ship> byIsUsed(Boolean isUsed) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("isUsed"), isUsed);
        });
    }

    public static Specification<Ship> withMinSpeed(Double speed) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), speed);
        });
    }

    public static Specification<Ship> withMaxSpeed(Double speed) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("speed"), speed);
        });
    }

    public static Specification<Ship> withMaxCrewSize(Integer size) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), size);
        });
    }

    public static Specification<Ship> withMinCrewSize(Integer size) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), size);
        });
    }

    public static Specification<Ship> withMinRating(Double rating) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
        });
    }

    public static Specification<Ship> withMaxRating(Double rating) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("rating"), rating);
        });
    }
}
