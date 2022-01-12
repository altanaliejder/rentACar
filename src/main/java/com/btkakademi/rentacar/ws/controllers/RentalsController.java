package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.RentalService;
import com.btkakademi.rentacar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkakademi.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
    private RentalService rentalService;
@Autowired
    public RentalsController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateRentalRequest createRentalRequest){
        return this.rentalService.add(createRentalRequest);
    }
}
