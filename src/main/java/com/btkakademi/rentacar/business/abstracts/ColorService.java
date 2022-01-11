package com.btkakademi.rentacar.business.abstracts;
import com.btkakademi.rentacar.business.dtos.ColorListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;

import java.util.List;

public interface ColorService {
    DataResult<List<ColorListDto>> getAll();
}
