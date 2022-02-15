package com.example.springbootdemo.springboot.Service;

import com.example.springbootdemo.springboot.DTO.CarDto;
import com.example.springbootdemo.springboot.Entity.CarEntity;
import com.example.springbootdemo.springboot.Repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public List<CarDto> getAllCar() {
        return carRepository.findAll().stream().map( car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarDto createCar(CarDto carDto) {
        CarEntity car =modelMapper.map(carDto, CarEntity.class);
        CarDto responseCar = modelMapper.map(carRepository.save(car), CarDto.class);
        return responseCar;
    }

    @Override
    public CarDto updateCar(long id, CarDto carDto) {
        CarEntity car = modelMapper.map(carDto, CarEntity.class);

        CarEntity cars = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car", "id", id));

        cars.setBrand(car.getBrand());
        cars.setModel(car.getModel());
        cars.setMake(car.getMake());
        cars.setYear(car.getYear());
        CarDto reponseCar = modelMapper.map(carRepository.save(cars), CarDto.class);
        return reponseCar;
    }

    @Override
    public void deleteCar(long id) {
        CarEntity car = carRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Car", "id", id));
        carRepository.delete(car);
    }

    @Override
    public CarDto getCarById(long id) {
        Optional<CarEntity> result = carRepository.findById(id);
        if(result.isPresent()){
            return modelMapper.map(result.get(), (Type) CarDto.class);
        } else{
            throw new ResourceNotFoundException("Car", "id", id);
        }
    }
}
