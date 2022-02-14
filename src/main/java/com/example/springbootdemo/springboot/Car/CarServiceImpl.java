package com.example.springbootdemo.springboot.Car;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        super();
        this.carRepository = carRepository;
    }

    public class ResourceNotFoundException extends RuntimeException {

        public ResourceNotFoundException(String message, String id, long l) {
            super(message);
        }

    }
    @Override
    public List<CarEntity> getAllCar() {
        return carRepository.findAll();
    }

    @Override
    public CarEntity createCar(CarEntity car) {
        return carRepository.save(car);
    }

    @Override
    public CarEntity updateCar(long id, CarEntity car) {
        CarEntity cars = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car", "id", id));

        cars.setBrand(car.getBrand());
        cars.setModel(car.getModel());
        cars.setMake(car.getMake());
        cars.setYear(car.getYear());
        return carRepository.save(cars);
    }

    @Override
    public void deleteCar(long id) {
        CarEntity car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car", "id", id));
        carRepository.delete(car);
    }

    @Override
    public CarEntity getCarById(long id) {
        Optional<CarEntity> result = carRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        } else{
            throw new ResourceNotFoundException("Car", "id", id);
        }
    }
}
