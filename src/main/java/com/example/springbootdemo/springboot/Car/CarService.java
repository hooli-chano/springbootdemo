package com.example.springbootdemo.springboot.Car;

import java.util.List;

public interface CarService {
    List<CarEntity> getAllCar();
    CarEntity createCar(CarEntity car);
    CarEntity updateCar(long id, CarEntity car);
    void deleteCar(long id);
    CarEntity getCarById(long id);
}
