package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.customerRequests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkakademi.rentacar.core.utilities.results.Result;

public interface IndividualCustomerService {
    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
}
