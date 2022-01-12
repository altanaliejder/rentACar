package com.btkakademi.rentacar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User{
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Rental> rentals;
}


//1. daha önce kayıtlı email kayıt olamaz;
//2. doğum tarihi 18den büyük olmalı