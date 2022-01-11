package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.dtos.CarListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;

import java.util.List;

public interface CarService {
    DataResult<List<CarListDto>> getAll();
}
