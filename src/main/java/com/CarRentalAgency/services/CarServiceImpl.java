package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
  private final CarRepository carRepository;

  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @Override
  public Car saveCar(Car car) {
    Optional<Car> existingCar = carRepository.findCarByRegistrationNumber(car.getRegistrationNumber());
    if (existingCar.isPresent()) {
      throw new AlreadyExistsException("The registration number " + car.getRegistrationNumber() + " was used by another car");
    }
    return carRepository.save(car);
  }

  @Override
  public void deleteCarById(Long id) {
    Optional<Car> car = carRepository.findById(id);
    if (car.isPresent()) {
      carRepository.deleteCarById(id);
    } else {
      throw new NoSuchElementException("Car not found with ID: " + id);
    }
  }

  @Override
  public Car updateCar(Long id, Car newCar) {
    Optional<Car> existingCar = carRepository.findById(id);
    if (existingCar.isPresent()) {
      Optional<Car> carWithSameRegistrationNumber = carRepository.findCarByRegistrationNumber(newCar.getRegistrationNumber());
      if (carWithSameRegistrationNumber.isPresent() && !carWithSameRegistrationNumber.get().getId().equals(id)) {
        throw new AlreadyExistsException("The registration number " + newCar.getRegistrationNumber() + " was used by another car");
      }
      Car carToUpdate = existingCar.get();
      carToUpdate.setRegistrationNumber(newCar.getRegistrationNumber());
      carToUpdate.setName(newCar.getName());
      carToUpdate.setModel(newCar.getModel());
      carToUpdate.setSeats(newCar.getSeats());
      carToUpdate.setGear(newCar.getGear());
      carToUpdate.setDoors(newCar.getDoors());
      carToUpdate.setKilometres(newCar.getKilometres());
      carToUpdate.setFuel(newCar.getFuel());
      return carRepository.save(carToUpdate);
    } else {
      throw new NoSuchElementException("Car not found with ID: " + id);
    }
  }

  @Override
  public List<Car> findAll() {
    return carRepository.findAll();
  }

  @Override
  public Car findCarById(long id) {
    Optional<Car> car = carRepository.findById(id);
    if (car.isPresent()) {
      return car.get();
    } else {
      throw new NoSuchElementException("Car not found with ID: " + id);
    }
  }

  @Override
  public Car findCarByRegistrationNumber(int registrationNumber) {
    Optional<Car> car = carRepository.findCarByRegistrationNumber(registrationNumber);
    if (car.isPresent()) {
      return car.get();
    } else {
      throw new NoSuchElementException("Car not found with registration number: " + registrationNumber);
    }
  }

  @Override
  public List<Car> findCarsByCarName(String carName) {
    return carRepository.findCarsByCarName(carName);
  }

  @Override
  public List<Car> findCarsByKilometresLessThanEqual(int kilometre) {
    return carRepository.findCarsByKilometresLessThanEqual(kilometre);
  }

  @Override
  public List<Car> findCarsByKilometresGreaterThanEqual(int kilometre) {
    return carRepository.findCarsByKilometresGreaterThanEqual(kilometre);
  }

  @Override
  public List<Car> findCarsByModel(Car.Model model) {
    return carRepository.findCarsByModel(model);
  }
}