package com.space.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ship")
public class Ship {
    @Id
    private Long id;
    private String name;
    private String planet;
    private Timestamp prodDate;
    @Enumerated(EnumType.STRING)
    private ShipType shipType;
    private Boolean isUsed;
    private Double speed;
    private Integer crewSize;
    private Double rating;

    public Ship(String name) {
        this.name = name;
    }

    public Ship() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Timestamp getProdDate() {
        return prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public Double getSpeed() {
        return speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public Double getRating() {
        return rating;
    }
}
