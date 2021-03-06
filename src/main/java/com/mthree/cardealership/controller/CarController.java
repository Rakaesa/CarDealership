/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDaoDB;
import com.mthree.cardealership.dao.CarModelDao;
import com.mthree.cardealership.dao.MakeDaoDB;
import com.mthree.cardealership.entities.Car;
import com.mthree.cardealership.entities.CarModel;
import com.mthree.cardealership.entities.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @Autowired
    MakeDaoDB makeDao;
    
    @Autowired
    CarModelDao carModelDao;
    @GetMapping("home/car")
    public String redirectHomeCar(){
        return "redirect:/sales/car";
    }
    
    @GetMapping("sales/car")
    public String getAllCars(Model model) {
        List<Car> allCars = dao.getAllCars();
        System.out.println(allCars);
        model.addAttribute("cars", allCars);
        return "car";
        
    }

    @GetMapping("admin/addcar")
    public String showAddCarPage(){
        return "addcar";
    }
    @PostMapping("admin/addcar")
    public String addCar(@Valid Car c, BindingResult result, HttpServletRequest req, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("flag", "Error");
            model.addAttribute("car", c);
            return "addCar";
        }
        
        System.out.println(c);
        String year = req.getParameter("year");
        String modelID = req.getParameter("modelId");
        String type = req.getParameter("type");
        String msrp = req.getParameter("msrp");
        String price = req.getParameter("price");
        String vin = req.getParameter("vin");
        String interior = req.getParameter("interiorColor");
        c.setInteriorColor(interior);
        c.setTransmission(req.getParameter("transmission"));
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
        
        model.addAttribute("success", "Car added successfully!");        
        return "addcar";
    }

    @GetMapping("home/car/{id}")
    public String contactDetail(@PathVariable int id, Model m) {
        Car c = dao.getCarById(id);
        m.addAttribute("car", c);
        CarModel carModelById = carModelDao.getCarModelById(c.getModelID());
        m.addAttribute("model", carModelById.getModel());
        return "viewcar";
    }

    @DeleteMapping("admin/car/{id}")
    public String deleteCarById(@PathVariable Integer id) {
        dao.deleteCarById(id);
        return "car";
    }
    
    @GetMapping("home/car/below15")
    public String viewCarsBelow15K(Model m){
        List<Car> cars = dao.getAllCars().stream().filter(x -> x.getPrice() < 15000).collect(Collectors.toList());
        m.addAttribute("cars", cars);
        m.addAttribute("header", "Below 15K");
        m.addAttribute("subheader", "Absolutely affordable");
        return "car";
    }
    
    @GetMapping("home/car/below25")
    public String viewCarsBelow25K(Model m){
        List<Car> cars = dao.getAllCars().stream().filter(x -> x.getPrice() < 25000).collect(Collectors.toList());
        m.addAttribute("cars", cars);
        m.addAttribute("header", "Below 25K");
        m.addAttribute("subheader", "The sweet spot");
        return "car";
    }
    
    @GetMapping("home/car/above25")
    public String viewCarsAbove25K(Model m){
        List<Car> cars = dao.getAllCars().stream().filter(x -> x.getPrice() > 25000).collect(Collectors.toList());
        m.addAttribute("cars", cars);
        m.addAttribute("header", "Above 25K");
        m.addAttribute("subheader", "Hey High Roller");
        return "car";
    }
    
    @GetMapping("home/car/new")
    public String getNewCars(Model m){
        List<Car> cars = dao.getAllCars().stream().filter(x -> x.getType().equalsIgnoreCase("new")).collect(Collectors.toList());
        m.addAttribute("cars", cars);
        m.addAttribute("header", "New Cars");
        m.addAttribute("subheader", "Browse our brand new vehicles");
        return "car";
    }
    
    @GetMapping("home/car/used")
    public String getUsedCars(Model m){
        List<Car> cars = dao.getAllCars().stream().filter(x -> x.getType().equalsIgnoreCase("used")).collect(Collectors.toList());
        m.addAttribute("cars", cars);
        m.addAttribute("header", "Used Cars");
        m.addAttribute("subheader", "Browse our fine used cars");
        return "car";
    }
    
    
    @PostMapping("home/car/search")
    public String getResults(Model m, HttpServletRequest request){
        List<Car> cars = dao.getAllCars();
        List<Car> results = new ArrayList<Car>();
        for (Car car : cars) {
            CarModel model = carModelDao.getCarModelById(car.getModelID());
            String comp = model.getModel() + makeDao.getMakeById(model.getMakeID()).getMake() + car.getYear(); 
            comp = comp.toLowerCase();
            if(comp.contains(request.getParameter("searchTerm").toLowerCase())){
                results.add(car);
            }
        }
        m.addAttribute("cars", results);
        return "car";
    }
    
    @GetMapping("admin/car/edit/{id}")
    public String showEditPage(Model m,@PathVariable int id){
        Car carById = dao.getCarById(id);
        m.addAttribute("car", carById);
        return "editCar";
    }
    
    @PostMapping("admin/car/edit/{id}")
    public String handleEditPage(Model m, @PathVariable int id, HttpServletRequest req){
        Car c = dao.getCarById(id);
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
        String isFeaturedString = req.getParameter("isFeatured");
        boolean isFeautred = Boolean.parseBoolean(isFeaturedString.toLowerCase());
        c.setIsFeatured(isFeautred);
        c.setVin(vin);
        c.setPrice(Double.parseDouble(price));
        c.setMsrp(Double.parseDouble(msrp));
        c.setType(type);
        c.setModelID(Integer.parseInt(modelID));
        c.setYear(Integer.parseInt(year));
        dao.updateCarByID(id, modelID, year, type, msrp, price, vin, interior, c.getTransmission(), c.getColor(), c.getBodyStyle(), c.getDescription(), String.valueOf(c.isIsFeatured()));
        c = dao.getCarById(id);
        m.addAttribute("car", c);
        return "editCar";
    }
    @GetMapping("admin/car")
    public String showAdminPage(Model m, HttpServletRequest request){
        List<Car> cars = dao.getAllCars();
        m.addAttribute("cars", cars);
        m.addAttribute("header", "Admin");
        m.addAttribute("subheader", "Edit or Delete a car");
        return "carAdmin";
    }
    
    @PostMapping("admin/car/search")
    public String handleAdminSearch(Model m, HttpServletRequest request){
        List<Car> cars = dao.getAllCars();
        List<Car> results = new ArrayList<Car>();
        for (Car car : cars) {
            CarModel model = carModelDao.getCarModelById(car.getModelID());
            String comp = model.getModel() + makeDao.getMakeById(model.getMakeID()).getMake() + car.getYear(); 
            comp = comp.toLowerCase();
            if(comp.contains(request.getParameter("searchTerm").toLowerCase())){
                results.add(car);
            }
        }
        m.addAttribute("cars", results);
        m.addAttribute("header", "Admin");
        m.addAttribute("subheader", "Edit or Delete a car");
        return "carAdmin";
    }
}
