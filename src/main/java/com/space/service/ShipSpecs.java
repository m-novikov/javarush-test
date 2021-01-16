package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.sun.istack.internal.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Time;
import java.sql.Timestamp;

public class ShipSpecs {
    public static Specification<Ship> byName(@NotNull String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        });
    }

    public static Specification<Ship> byPlanet(@NotNull String planet) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("planet"), "%" + planet + "%");
        });
    }

    public static Specification<Ship> afterProdDate(@NotNull Long after) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), new Timestamp(after));
        });
    }

    public static Specification<Ship> beforeProdDate(@NotNull Long before) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), new Timestamp(before));
        });
    }

    public static Specification<Ship> byShipType(@NotNull ShipType shipType) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("shipType"), shipType);
        });
    }

    public static Specification<Ship> byIsUsed(@NotNull Boolean isUsed) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("isUsed"), isUsed);
        });
    }

    public static Specification<Ship> withMinSpeed(@NotNull Double speed) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), speed);
        });
    }

    public static Specification<Ship> withMaxSpeed(@NotNull Double speed) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("speed"), speed);
        });
    }

    public static Specification<Ship> withMaxCrewSize(@NotNull Integer size) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), size);
        });
    }

    public static Specification<Ship> withMinCrewSize(@NotNull Integer size) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), size);
        });
    }

    public static Specification<Ship> withMinRating(@NotNull Double rating) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
        });
    }

    public static Specification<Ship> withMaxRating(@NotNull Double rating) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("rating"), rating);
        });
    }
}
