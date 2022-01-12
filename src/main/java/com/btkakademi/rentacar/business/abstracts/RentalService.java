package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkakademi.rentacar.core.utilities.results.Result;

public interface RentalService {
    Result add(CreateRentalRequest createRentalRequest);
}
