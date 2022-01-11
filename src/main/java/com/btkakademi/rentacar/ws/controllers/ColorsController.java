package com.btkakademi.rentacar.ws.controllers;

import com.btkakademi.rentacar.business.abstracts.ColorService;
import com.btkakademi.rentacar.business.dtos.BrandListDto;
import com.btkakademi.rentacar.business.dtos.ColorListDto;
import com.btkakademi.rentacar.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
    private ColorService colorService;

@Autowired
    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("getall")
    public DataResult<List<ColorListDto>> getAll(){
        return this.colorService.getAll();
    }
}
