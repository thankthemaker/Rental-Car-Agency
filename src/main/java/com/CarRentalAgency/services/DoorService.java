package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Door;

import java.util.List;

public interface DoorService {

    List<Door> findAll();

    Door saveDoor(Door door);

    Door updateDoor(Long id, Door door);
}
