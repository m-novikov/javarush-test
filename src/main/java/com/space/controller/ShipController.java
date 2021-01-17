package com.space.controller;

import com.space.controller.dto.ShipQueryDTO;
import com.space.model.Ship;
import com.space.service.ShipService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rest/ships")
public class ShipController {
    private final ShipService service;

    public ShipController(ShipService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ship> getShips(ShipQueryDTO query,
                               ShipOrder order,
                               @PageableDefault(value=3, page=0) Pageable pageable) {

        if (order != null) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(order.getFieldName()));
        }
        return service.findAll(query, pageable).getContent();
    }

    @PostMapping
    public Ship createShip(@RequestBody Ship ship) {
        try {
            return service.createShip(ship);
        } catch (ShipService.InvalidShipException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship updateShip(@PathVariable Long id, @RequestBody Ship ship) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            return service.updateShip(id, ship);
        } catch (ShipService.InvalidShipException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (ShipService.ShipNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/{id}")
    public Ship getSingleShip(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            return service.findById(id);
        } catch (ShipService.ShipNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/count")
    public long getShipCount(ShipQueryDTO query) {
        return service.count(query);
    }

    @DeleteMapping(value="/{id}")
    public void deleteShip(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        try {
            service.deleteById(id);
        } catch (ShipService.ShipNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
