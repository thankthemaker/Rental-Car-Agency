package com.CarRentalAgency.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Door {

    @Id
    @SequenceGenerator(
            name = "door_seq",
            sequenceName = "door_seq",
            allocationSize = 1,
            initialValue = 18 // start from 18 because we have 17 cars in the database.
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "door_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    DoorStatus doorStatus;

    enum DoorStatus {
        OPEN,
        CLOSED
    }
}
