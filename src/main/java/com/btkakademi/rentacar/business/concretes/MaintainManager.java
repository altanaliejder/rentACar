package com.btkakademi.rentacar.business.concretes;
import com.btkakademi.rentacar.business.abstracts.MaintainService;
import com.btkakademi.rentacar.business.dtos.MaintainListDto;
import com.btkakademi.rentacar.business.requests.maintainRequests.CreateMaintainRequest;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.Result;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.MaintainDao;
import com.btkakademi.rentacar.entities.concretes.Maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintainManager implements MaintainService {
    private MaintainDao maintainDao;
    private ModelMapperService modelMapperService;
    @Autowired
    public MaintainManager(MaintainDao maintainDao, ModelMapperService modelMapperService) {
        this.maintainDao = maintainDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateMaintainRequest createMaintainRequest) {
        var maintain=this.modelMapperService.forRequest().map(createMaintainRequest, Maintain.class);
        this.maintainDao.save(maintain);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<Maintain>> getAll(MaintainListDto maintainListDto) {
        var maintainList=this.maintainDao.findAll();
        var maintainDto=maintainList.stream().map(maintain -> this.modelMapperService.forDto().map(maintain,Maintain.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<Maintain>>(maintainDto);
    }

    @Override
    public DataResult<List<Maintain>> getMaintainByCarId(int carId) {
        return new SuccessDataResult<List<Maintain>>(this.maintainDao.getMaintainByCarId(carId));
    }

    @Override
    public DataResult<List<Maintain>> getMaintanenceCar(int carId) {
        return new SuccessDataResult<List<Maintain>>(this.maintainDao.getMaintanenceCar(carId));
    }
}
