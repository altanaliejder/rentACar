package com.btkakademi.rentacar.business.abstracts;

import com.btkakademi.rentacar.business.requests.customerRequests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkakademi.rentacar.core.utilities.results.Result;

public interface CorporateCustomerService {
    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
}
