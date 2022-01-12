package com.btkakademi.rentacar.dataAccess.abstratcs;

import com.btkakademi.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDao extends JpaRepository<Rental,Integer> {
}
