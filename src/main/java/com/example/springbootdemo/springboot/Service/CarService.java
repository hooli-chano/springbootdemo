package com.example.springbootdemo.springboot.Service;

import com.example.springbootdemo.springboot.DTO.CarDto;
import com.example.springbootdemo.springboot.Entity.CarEntity;

import java.util.List;

public interface CarService {
    List<CarDto> getAllCar();
    CarDto createCar(CarDto carDto);
    CarDto updateCar(long id, CarDto carDto);
    void deleteCar(long id);
    CarDto getCarById(long id);
}
