package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CarService;
import com.btkakademi.rentacar.business.constants.Messages;
import com.btkakademi.rentacar.business.dtos.CarListDto;
import com.btkakademi.rentacar.business.requests.carRequests.CreateCarRequest;
import com.btkakademi.rentacar.business.requests.carRequests.UpdateCarRequest;
import com.btkakademi.rentacar.core.utilities.business.BusinessRules;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.*;
import com.btkakademi.rentacar.dataAccess.abstratcs.CarDao;
import com.btkakademi.rentacar.entities.concretes.Brand;
import com.btkakademi.rentacar.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

    private CarDao carDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CarListDto>> getAll() {
        List<Car> carList= this.carDao.findAll();
        List<CarListDto> response=carList.stream().map(car -> modelMapperService.forDto().map(car,CarListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarListDto>>(response);
    }

    @Override
    public Result add(CreateCarRequest createCarRequest) {
        Result result= BusinessRules.run();

        if(result!=null){
            return result;
        }
        var car=modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult(Messages.CAR_ADDED);
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Car car= modelMapperService.forRequest().map(updateCarRequest,Car.class);
        var response=BusinessRules.run(checkIfCarIdExists(updateCarRequest.getId()));
        System.out.println("sadsadsa"+response);
        if(response==null){
            this.carDao.save(car);
            return new SuccessResult();
        }return new ErrorResult();
    }

    private Result checkIfCarIdExists(int carId){
        Car car= this.carDao.getCarById(carId);
        if(car!=null){
            return new SuccessResult();
        }return new ErrorResult("Böyle bir kullanıcı yok");
    }
}
