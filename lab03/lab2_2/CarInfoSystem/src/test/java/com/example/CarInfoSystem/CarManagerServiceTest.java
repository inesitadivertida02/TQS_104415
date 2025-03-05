package com.example.CarInfoSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings
public class CarManagerServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    void setUp() {
        Car car1 = new Car("Mazda", "Mx5");
        car1.setCarId(1L);
        Car car2 = new Car("Toyota", "Corolla");
        car2.setCarId(2L);
        Car car3 = new Car("Honda", "Civic");
        car3.setCarId(3L);
        Car car4 = new Car("Ford", "Focus");
        car4.setCarId(4L);
        List<Car> cars = List.of(car1, car2, car3, car4);

        when(carRepository.findAll()).thenReturn(cars);
        when(carRepository.findByCarId(1L)).thenReturn(car1);
        when(carRepository.findByCarId(2L)).thenReturn(car2);
        when(carRepository.findByCarId(3L)).thenReturn(car3);
        when(carRepository.findByCarId(4L)).thenReturn(car4);
        when(carRepository.findByCarId(5L)).thenReturn(null);  // Mock for invalid ID
    }

    @Test
    void whenGetAllCars_thenReturnAllCars() {
        List<Car> allCars = carManagerService.getAllCars();

        assertThat(allCars)
                .hasSize(4)
                .extracting("maker")
                .contains("Mazda", "Toyota", "Honda", "Ford");
    }

    @Test
    void whenGetCarDetails_thenReturnCarDetails() {
        Car carDetails = carManagerService.getCarDetails(1L)
                .orElseThrow(() -> new AssertionError("Car not found"));

        // Verify the mock was called
        Mockito.verify(carRepository).findByCarId(1L);

        assertThat(carDetails.getMaker())
                .isEqualTo("Mazda");
    }

    @Test
    void whenCarInvalidId_thenCarShouldNotBeFound() {
        assertThat(carManagerService.getCarDetails(5L)).isEmpty();
    }
}