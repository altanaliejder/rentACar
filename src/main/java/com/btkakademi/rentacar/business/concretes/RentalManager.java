package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CustomerService;
import com.btkakademi.rentacar.business.abstracts.MaintainService;
import com.btkakademi.rentacar.business.abstracts.RentalService;
import com.btkakademi.rentacar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.ErrorResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.core.utilities.results.SuccessResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.RentalDao;
import com.btkakademi.rentacar.entities.concretes.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class RentalManager implements RentalService {
    private ModelMapperService modelMapperService;
    private RentalDao rentalDao;
    private CustomerService customerService;
    private MaintainService maintainService;
    @Autowired
    public RentalManager(ModelMapperService modelMapperService, RentalDao rentalDao, CustomerService customerService, MaintainService maintainService) {
        this.modelMapperService = modelMapperService;
        this.rentalDao = rentalDao;
        this.customerService = customerService;
        this.maintainService = maintainService;
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {
        var rental= modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        var response= BusinessRules.run(checkIfCustomerIdExists(createRentalRequest.getCustomerId()),
                checkKilometer(createRentalRequest.getRentKilometer(),
                        createRentalRequest.getReturnedKilometer()),
                checkRentalDate(createRentalRequest.getRentDate(),
                        createRentalRequest.getReturnDate()),
                checkIfCarIsMaintainence(createRentalRequest.getCarId())
                );
        if(response==null){
            this.rentalDao.save(rental);
            return new SuccessResult();
        }return new ErrorResult(response.getMessage());

    }


    private Result checkRentalDate(LocalDate rentDate,LocalDate returnDate){
        if(rentDate.isAfter(returnDate)){
           return new ErrorResult("Kiralama tarihi dönüş tarihinden sonra olamaz");
        }return new SuccessResult();
    }
    private Result checkKilometer(int rentKilometer,int returnKilometer ){
        if(!(rentKilometer<returnKilometer)){
            return new ErrorResult("Kiralama km'si Dönüş kmden yüksek olamaz");
        }return new SuccessResult();
    }
    private Result checkIfCustomerIdExists(int id){
        var customer=this.customerService.getCustomerById(id).getData();
        if(customer==null){
            return new ErrorResult("Böyle Bir kullanıcı yok");
        }return new SuccessResult();
    }

    private Result checkIfCarIsMaintainence(int carId){
        var maintainence=this.maintainService.getMaintanenceCar(carId).getData();
        if(maintainence!=null){
            return new ErrorResult("Araba bakımda kiralanamaz");
        }return new SuccessResult();
    }
}
