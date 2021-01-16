package com.space.controller;

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
    public List<Ship> getShips(ShipService.ShipQuery query,
                               ShipOrder order,
                               @PageableDefault(value=3, page=0) Pageable pageable) {

        if (order != null) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(order.getFieldName()));
        }
        System.out.printf("query: " + query);
        return service.findAll(query, pageable).getContent();
    }

    @GetMapping(value="/{id}")
    public Ship getShipCount(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value="/count")
    public long getShipCount(ShipService.ShipQuery query) {
        return service.count(query);
    }
}
