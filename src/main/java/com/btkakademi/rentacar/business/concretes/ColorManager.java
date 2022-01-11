package com.btkakademi.rentacar.business.concretes;

import com.btkakademi.rentacar.business.abstracts.ColorService;
import com.btkakademi.rentacar.business.dtos.ColorListDto;
import com.btkakademi.rentacar.core.utilities.mapping.ModelMapperService;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import com.btkakademi.rentacar.core.utilities.results.SuccessDataResult;
import com.btkakademi.rentacar.dataAccess.abstratcs.ColorDao;
import com.btkakademi.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ColorManager implements ColorService {

    private ColorDao colorDao;
    private ModelMapperService modelMapperService;
    @Autowired
    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ColorListDto>> getAll() {
        List<Color> colorList= this.colorDao.findAll();
        List<ColorListDto> response= colorList.stream().map(color -> modelMapperService.forDto().map(color,ColorListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ColorListDto>>(response);
    }
}
