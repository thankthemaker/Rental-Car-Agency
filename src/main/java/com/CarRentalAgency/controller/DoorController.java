package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Door;
import com.CarRentalAgency.services.DoorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/doors")
public class DoorController {

    private final DoorService doorService;

    public DoorController(DoorService doorService) {
        this.doorService = doorService;
    }

    @PutMapping("{id}")
    public ResponseEntity<Door> updateDoor(@PathVariable Long id, @Valid @RequestBody Door door) {
        Door updatedDoor = doorService.updateDoor(id, door);
        return new ResponseEntity<>(updatedDoor, HttpStatus.OK);
    }
}