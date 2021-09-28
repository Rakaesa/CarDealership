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
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author conno
 */
@Controller
public class CarController {
    
    @Autowired
    CarDaoDB dao;

    @GetMapping("car")
    public String getAllCars(Model model) {
        List<Car> allCars = dao.getAllCars();
        System.out.println(allCars);
        model.addAttribute("cars", allCars);
        return "car";
        
    }

    @GetMapping("addcar")
    public String showAddCarPage(){
        return "addcar";
    }
    @PostMapping("addcar")
    public String addCar(Car c, HttpServletRequest req) {
        System.out.println(c);
        String year = req.getParameter("year");
        String modelID = req.getParameter("modelId");
        String type = req.getParameter("type");
        String msrp = req.getParameter("msrp");
        String price = req.getParameter("price");
        String vin = req.getParameter("vin");
        String interior = req.getParameter("interior");
        c.setInteriorColor(interior);
        c.setTransmission(req.getParameter("trans"));
        c.setColor(req.getParameter("color"));
        c.setBodyStyle(req.getParameter("bodyStyle"));
        c.setDescription(req.getParameter("description"));
        c.setIsFeatured(true);
        c.setVin(vin);
        c.setPrice(Double.parseDouble(price));
        c.setMsrp(Double.parseDouble(msrp));
        c.setType(type);
        c.setModelID(Integer.parseInt(modelID));
        c.setYear(Integer.parseInt(year));
        Car created = dao.addCar(c);
        
        return "addcar";
    }

    @GetMapping("car/{id}")
    public String contactDetail(@PathVariable int id, Model m) {
        Car c = dao.getCarById(id);
        m.addAttribute("car", c);
        return "viewcar";
    }

    @DeleteMapping("car/{id}")
    public String deleteCarById(@PathVariable Integer id) {
        dao.deleteCarById(id);
        return "car";
    }
}
