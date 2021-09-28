/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDaoDB;
import com.mthree.cardealership.dao.ContactDao;
import com.mthree.cardealership.entities.Car;
import com.mthree.cardealership.entities.Contact;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author conno
 */
@Controller
public class CarController {
    
    @Autowired
    CarDaoDB dao;

    @GetMapping("car")
    public List<Car> getAllCars() {
        return dao.getAllCars();
    }

    @PostMapping("car")
    public String addCar(Car c) {
        Car created = dao.addCar(c);
        return created.toString();
    }

    @GetMapping("car/{id}")
    public String contactDetail(@PathVariable int id) {
        Car c = dao.getCarById(id);
        return c.toString();
    }

    @DeleteMapping("car")
    public String deleteCarById(Integer id) {
        dao.deleteCarById(id);
        return "Deleted!";
    }
}
