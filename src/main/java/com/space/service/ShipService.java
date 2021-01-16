package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.sun.istack.internal.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        public Boolean getUsed() {
            return isUsed;
        }

        public void setUsed(Boolean used) {
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

    public Page<Ship> findAll(@NotNull ShipQuery query, @NotNull Pageable pageable) {
        System.out.println("findAll: " + query);
        System.out.println("page: " + pageable);
        return repository.findAll(pageable);
    }

    public long count(@NotNull ShipQuery query) {
        System.out.println("count: " + query);
        return repository.count();
    }
}
