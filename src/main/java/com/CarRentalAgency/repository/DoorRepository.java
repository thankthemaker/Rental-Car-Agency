package com.CarRentalAgency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.CarRentalAgency.entity.Door;

@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {
}
