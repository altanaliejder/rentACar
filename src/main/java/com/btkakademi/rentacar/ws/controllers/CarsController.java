package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.CarService;
import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.dtos.CarListDto;
import com.btkakademi.rentacar.business.requests.brandRequests.CreateBrandRequest;
import com.btkakademi.rentacar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkakademi.rentacar.business.requests.carRequests.CreateCarRequest;
import com.btkakademi.rentacar.business.requests.carRequests.UpdateCarRequest;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private CarService carService;

    @Autowired
    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("getall")
    public DataResult<List<CarListDto>> getAll(){
        return this.carService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCarRequest createCarRequest){
        return this.carService.add(createCarRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody UpdateCarRequest updateCarRequest){
        System.out.println(updateCarRequest.getId());
        return this.carService.update(updateCarRequest);
    }
}
