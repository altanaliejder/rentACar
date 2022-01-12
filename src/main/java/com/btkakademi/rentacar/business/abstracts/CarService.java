package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.dtos.CarListDto;
import com.btkakademi.rentacar.business.requests.brandRequests.CreateBrandRequest;
import com.btkakademi.rentacar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkakademi.rentacar.business.requests.carRequests.CreateCarRequest;
import com.btkakademi.rentacar.business.requests.carRequests.UpdateCarRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;

import java.util.List;

public interface CarService {
    DataResult<List<CarListDto>> getAll();
    Result add(CreateCarRequest createCarRequest);
    Result update(UpdateCarRequest updateCarRequest);

}
