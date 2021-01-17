package com.space.service;

import com.space.controller.dto.ShipQueryDTO;
import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Service
public class ShipService {
    public static final class InvalidShipException extends Exception {}
    public static final class ShipNotFoundException extends Exception {}
    private final ShipRepository repository;

    public ShipService(ShipRepository repository) {
        this.repository = repository;
    }
    private Specification<Ship> convertQueryToSpec(ShipQueryDTO query) {
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

    public Page<Ship> findAll(ShipQueryDTO query, Pageable pageable) {
        Specification<Ship> spec = convertQueryToSpec(query);
        return repository.findAll(spec, pageable);
    }

    public long count(ShipQueryDTO query) {
        Specification<Ship> spec = convertQueryToSpec(query);
        return repository.count(spec);
    }

    private void validateStringOfLength50(String str) throws InvalidShipException {
        if (str == null || "".equals(str) || str.length() > 50) throw new InvalidShipException();
    }

    private void validateYear(Integer year) throws InvalidShipException {
        if (year == null || 2800 > year || year > 3019) throw new InvalidShipException();
    }

    private void validateCrewSize(Integer size) throws InvalidShipException {
        if (size == null || 1 > size || size > 9999) throw new InvalidShipException();
    }

    private Double validateSpeed(Double speed) throws InvalidShipException {
        if (speed == null) {
            throw new InvalidShipException();
        } else {
            Double roundedSpeed = Math.round(speed * 100.0) / 100.0;
            if (0.01 > roundedSpeed || roundedSpeed > 0.99) throw new InvalidShipException();
            return roundedSpeed;
        }
    }

    private void validateShip(Ship ship) throws InvalidShipException {
        validateStringOfLength50(ship.getName());
        validateStringOfLength50(ship.getPlanet());
        validateYear(ship.getYear());
        validateCrewSize(ship.getCrewSize());
        ship.setSpeed(validateSpeed(ship.getSpeed()));
        if (ship.getIsUsed() == null) {
            ship.setIsUsed(false);
        }
        double k = ship.getIsUsed() ? 0.5 : 1;
        double rating = 80 * ship.getSpeed() * k / (3019 - ship.getYear() + 1);
        ship.setRating(Math.round(rating * 100.0) / 100.0);
    }

    public Ship createShip(Ship ship) throws InvalidShipException {
        validateShip(ship);
        return repository.save(ship);
    }

    public Ship updateShip(Long id, Ship newShip) throws InvalidShipException, ShipNotFoundException {
        Ship ship = repository.findById(id).orElseThrow(() -> new ShipNotFoundException());
        if (newShip.getName() != null) ship.setName(newShip.getName());
        if (newShip.getPlanet() != null) ship.setPlanet(newShip.getPlanet());
        if (newShip.getShipType() != null) ship.setShipType(newShip.getShipType());
        if (newShip.getProdDate() != null) ship.setProdDate(newShip.getProdDate());
        if (newShip.getIsUsed() != null) ship.setIsUsed(newShip.getIsUsed());
        if (newShip.getSpeed() != null) ship.setSpeed(newShip.getSpeed());
        if (newShip.getCrewSize() != null) ship.setCrewSize(newShip.getCrewSize());
        validateShip(ship);
        return repository.save(ship);
    }

    public Ship findById(Long id) throws ShipNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ShipNotFoundException());
    }

    public void deleteById(Long id) throws ShipNotFoundException {
        if (!repository.existsById(id)) throw new ShipNotFoundException();
        repository.deleteById(id);
    }
}
