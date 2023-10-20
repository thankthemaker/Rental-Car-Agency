package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the CarService interface and provides the implementation for car-related operations.
 */
@Service
public class CarServiceImpl implements CarService {
  private final CarRepository carRepository;

  /**
   * Constructs a new CarServiceImpl with the specified CarRepository dependency.
   *
   * @param carRepository the car repository to be used for car data access
   */
  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  /**
   * Saves a car in the database.
   *
   * @param car the car to be saved
   * @return the saved car
   * @throws AlreadyExistsException if a car with the same registration number already exists
   */
  @Override
  public Car saveCar(Car car) {
    Optional<Car> existingCar = carRepository.findCarByRegistrationNumber(car.getRegistrationNumber());
    if (existingCar.isPresent()) {
      throw new AlreadyExistsException("The registration number " + car.getRegistrationNumber() + " was used by another car");
    }
    return carRepository.save(car);
  }

  /**
   * Deletes a car from the database by its ID.
   *
   * @param id the ID of the car to be deleted
   * @throws NoSuchElementException if the car with the specified ID does not exist
   */
  @Override
  public void deleteCarById(Long id) {
    Optional<Car> car = carRepository.findById(id);
    if (car.isPresent()) {
      carRepository.deleteCarById(id);
    } else {
      throw new NoSuchElementException("Car not found with ID: " + id);
    }
  }

  /**
   * Updates a car in the database with new information.
   *
   * @param id     the ID of the car to be updated
   * @param newCar the new car information
   * @return the updated car
   * @throws NoSuchElementException if the car with the specified ID does not exist
   * @throws AlreadyExistsException  if a car with the same registration number already exists
   */
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

  /**
   * Retrieves all cars from the database.
   *
   * @return a list of all cars
   */
  @Override
  public List<Car> findAll() {
    return carRepository.findAll();
  }

  /**
   * Retrieves a car from the database by its ID.
   *
   * @param id the ID of the car to retrieve
   * @return the car with the specified ID
   * @throws NoSuchElementException if the car with the specified ID does not exist
   */
  @Override
  public Car findCarById(long id) {
    Optional<Car> car = carRepository.findById(id);
    if (car.isPresent()) {
      return car.get();
    } else {
      throw new NoSuchElementException("Car not found with ID: " + id);
    }
  }

  /**
   * Retrieves a car from the database by its registration number.
   *
   * @param registrationNumber the registration number of the car to retrieve
   * @return the car with the specified registration number
   * @throws NoSuchElementException if the car with the specified registration number does not exist
   */
  @Override
  public Car findCarByRegistrationNumber(int registrationNumber) {
    Optional<Car> car = carRepository.findCarByRegistrationNumber(registrationNumber);
    if (car.isPresent()) {
      return car.get();
    } else {
      throw new NoSuchElementException("Car not found with registration number: " + registrationNumber);
    }
  }

  /**
   * Retrieves a list of cars from the database by their name.
   *
   * @param carName the name of the cars to retrieve
   * @return a list of cars with the specified name
   */
  @Override
  public List<Car> findCarsByCarName(String carName) {
    return carRepository.findCarsByCarName(carName);
  }

  /**
   * Retrieves a list of cars from the database with kilometers less than or equal to the specified value.
   *
   * @param kilometre the maximum kilometers value
   * @return a list of cars with kilometers less than or equal to the specified value
   */
  @Override
  public List<Car> findCarsByKilometresLessThanEqual(int kilometre) {
    return carRepository.findCarsByKilometresLessThanEqual(kilometre);
  }

  /**
   * Retrieves a list of cars from the database with kilometers greater than or equal to the specified value.
   *
   * @param kilometre the minimum kilometers value
   * @return a list of cars with kilometers greater than or equal to the specified value
   */
  @Override
  public List<Car> findCarsByKilometresGreaterThanEqual(int kilometre) {
    return carRepository.findCarsByKilometresGreaterThanEqual(kilometre);
  }

  /**
   * Retrieves a list of cars from the database with the specified model.
   *
   * @param model the model of the cars to retrieve
   * @return a list of cars with the specified model
   */
  @Override
  public List<Car> findCarsByModel(Car.Model model) {
    return carRepository.findCarsByModel(model);
  }
}