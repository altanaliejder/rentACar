package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.CarService;
import com.btkakademi.rentacar.business.dtos.CarListDto;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.CarDao;
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
}
