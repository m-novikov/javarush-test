package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.sun.istack.internal.NotNull;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {
    public static final class ShipQuery {
        private String name;
        private String planet;
        private ShipType shipType;
        private Long after;
        private Long before;
        private Boolean isUsed;
        private Double minSpeed;
        private Double maxSpeed;
        private Integer minCrewSize;
        private Integer maxCrewSize;
        private Double minRating;
        private Double maxRating;

        public ShipQuery() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlanet() {
            return planet;
        }

        public void setPlanet(String planet) {
            this.planet = planet;
        }

        public ShipType getShipType() {
            return shipType;
        }

        public void setShipType(ShipType shipType) {
            this.shipType = shipType;
        }

        public Long getAfter() {
            return after;
        }

        public void setAfter(Long after) {
            this.after = after;
        }

        public Long getBefore() {
            return before;
        }

        public void setBefore(Long before) {
            this.before = before;
        }

        public Boolean getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(Boolean used) {
            isUsed = used;
        }

        public Double getMinSpeed() {
            return minSpeed;
        }

        public void setMinSpeed(Double minSpeed) {
            this.minSpeed = minSpeed;
        }

        public Double getMaxSpeed() {
            return maxSpeed;
        }

        public void setMaxSpeed(Double maxSpeed) {
            this.maxSpeed = maxSpeed;
        }

        public Integer getMinCrewSize() {
            return minCrewSize;
        }

        public void setMinCrewSize(Integer minCrewSize) {
            this.minCrewSize = minCrewSize;
        }

        public Integer getMaxCrewSize() {
            return maxCrewSize;
        }

        public void setMaxCrewSize(Integer maxCrewSize) {
            this.maxCrewSize = maxCrewSize;
        }

        public Double getMinRating() {
            return minRating;
        }

        public void setMinRating(Double minRating) {
            this.minRating = minRating;
        }

        public Double getMaxRating() {
            return maxRating;
        }

        public void setMaxRating(Double maxRating) {
            this.maxRating = maxRating;
        }

        @Override
        public String toString() {
            return "ShipQuery{" +
                    "name='" + name + '\'' +
                    ", planet='" + planet + '\'' +
                    ", shipType=" + shipType +
                    ", after=" + after +
                    ", before=" + before +
                    ", isUsed=" + isUsed +
                    ", minSpeed=" + minSpeed +
                    ", maxSpeed=" + maxSpeed +
                    ", minCrewSize=" + minCrewSize +
                    ", maxCrewSize=" + maxCrewSize +
                    ", minRating=" + minRating +
                    ", maxRating=" + maxRating +
                    '}';
        }
    }
    private final ShipRepository repository;

    public ShipService(ShipRepository repository) {
        this.repository = repository;
    }
    private Specification<Ship> convertQueryToSpec(@NotNull ShipQuery query) {
        Specification<Ship> spec = Specification.where(null);
        if (query.getName() != null) spec = spec.and(ShipSpecs.byName(query.getName()));
        if (query.getPlanet() != null) spec = spec.and(ShipSpecs.byPlanet(query.getPlanet()));
        if (query.getShipType() != null) spec = spec.and(ShipSpecs.byShipType(query.getShipType()));
        if (query.getIsUsed() != null) spec = spec.and(ShipSpecs.byIsUsed(query.getIsUsed()));
        if (query.getAfter() != null) spec = spec.and(ShipSpecs.afterProdDate(query.getAfter()));
        if (query.getBefore() != null) spec = spec.and(ShipSpecs.beforeProdDate(query.getBefore()));
        if (query.getMinSpeed() != null) spec = spec.and(ShipSpecs.withMinSpeed(query.getMinSpeed()));
        if (query.getMaxSpeed() != null) spec = spec.and(ShipSpecs.withMaxSpeed(query.getMaxSpeed()));
        if (query.getMinCrewSize() != null) spec = spec.and(ShipSpecs.withMinCrewSize(query.getMinCrewSize()));
        if (query.getMaxCrewSize() != null) spec = spec.and(ShipSpecs.withMaxCrewSize(query.getMaxCrewSize()));
        if (query.getMinRating() != null) spec = spec.and(ShipSpecs.withMinRating(query.getMinRating()));
        if (query.getMaxRating() != null) spec = spec.and(ShipSpecs.withMaxRating(query.getMaxRating()));
        return spec;
    }

    public Page<Ship> findAll(@NotNull ShipQuery query, @NotNull Pageable pageable) {
        Specification<Ship> spec = convertQueryToSpec(query);
        return repository.findAll(spec, pageable);
    }

    public long count(@NotNull ShipQuery query) {
        Specification<Ship> spec = convertQueryToSpec(query);
        return repository.count(spec);
    }

    public Optional<Ship> findById(@NotNull Long id) {
        return repository.findById(id);
    }
}
