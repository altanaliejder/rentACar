package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car,Integer> {
    Car getCarById(int id);
}
