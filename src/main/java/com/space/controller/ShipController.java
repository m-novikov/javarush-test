package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.service.ShipService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ShipController {
    private final ShipService service;

    public ShipController(ShipService service) {
        this.service = service;
    }

    @RequestMapping(value="/rest/ships", method=GET)
    public List<Ship> getShips(ShipService.ShipQuery query, Pageable pageable) {
        return service.findAll(query, pageable).getContent();
    }

    @RequestMapping(value="/rest/ships/count", method=GET)
    public long getShipCount(ShipService.ShipQuery query) {
        return service.count(query);
    }
}
