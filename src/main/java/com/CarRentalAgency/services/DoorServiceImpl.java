package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Door;
import com.CarRentalAgency.repository.DoorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoorServiceImpl implements DoorService {

    private final DoorRepository doorRepository;

    public DoorServiceImpl(DoorRepository doorRepository) {
        this.doorRepository = doorRepository;
    }

    @Override
    public List<Door> findAll() {
        return doorRepository.findAll();
    }

    @Override
    public Door saveDoor(Door door) {
        return doorRepository.save(door);
    }


    @Override
    public Door updateDoor(Long id, Door door) {
        return doorRepository.save(door);
    }
}