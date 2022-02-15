package com.example.springbootdemo.springboot.RestController;

import com.example.springbootdemo.springboot.DTO.CarDto;
import com.example.springbootdemo.springboot.Entity.CarEntity;
import com.example.springbootdemo.springboot.Service.CarService;
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

    private CarService carService;

    public CarController(CarService carService) {
        super();
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getAllCars(){
        return carService.getAllCar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable(name = "id") Long id){
        CarDto carResponse = carService.getCarById(id);

        return ResponseEntity.ok().body(carResponse);
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto){

        CarDto convertedCar = carService.createCar(carDto);;

        return new ResponseEntity<CarDto>(convertedCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable long id, @RequestBody CarDto carDto){

        CarDto convertedCar = carService.updateCar(id, carDto);

        return ResponseEntity.ok().body(convertedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id){
         carService.deleteCar(id);

        return ResponseEntity.ok().body("Car Deleted!");
    }
}
