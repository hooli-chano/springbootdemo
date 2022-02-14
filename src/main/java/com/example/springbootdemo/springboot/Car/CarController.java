package com.example.springbootdemo.springboot.Car;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ModelMapper modelMapper;

    private CarService carService;

    public CarController(CarService carService) {
        super();
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getAllCars(){
        return carService.getAllCar().stream().map( car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable(name = "id") Long id){
        CarEntity car = carService.getCarById(id);

        CarDto carResponse = modelMapper.map(car, CarDto.class);

        return ResponseEntity.ok().body(carResponse);
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto){
        CarEntity car =modelMapper.map(carDto, CarEntity.class);

        CarEntity cars = carService.createCar(car);

        CarDto convertedCar = modelMapper.map(cars, CarDto.class);

        return new ResponseEntity<CarDto>(convertedCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable long id, @RequestBody CarDto carDto){
        CarEntity car = modelMapper.map(carDto, CarEntity.class);

        CarEntity cars = carService.updateCar(id, car);

        CarDto convertedCar = modelMapper.map(cars, CarDto.class);

        return ResponseEntity.ok().body(convertedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id){
         carService.deleteCar(id);

        return ResponseEntity.ok().body("Car Deleted!");
    }
}
