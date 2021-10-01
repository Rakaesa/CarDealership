/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDaoDB;
import com.mthree.cardealership.dao.CarModelDaoDB;
import com.mthree.cardealership.entities.Car;
import com.mthree.cardealership.entities.CarModel;
import com.mthree.cardealership.entities.Special;
import com.mthree.cardealership.service.SpecialService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ychen
 */
@Controller
public class homeSpecialContoller {
    
    @Autowired
    SpecialService specialService;
    
    @Autowired
    CarDaoDB carDaoDB;
    
    @Autowired
    CarModelDaoDB carModelDaoDB;
    
    @GetMapping("/")
    public String indexRedirect(){
        return "redirect:/home/index";
    }
    
    @GetMapping("/home")
    public String homeRedirect(){
        return indexRedirect();
    }
    
    @GetMapping("/home/index")
    public String homeRender(Model m){
        List<Car> cars = carDaoDB.getAllCars().stream().filter(x-> x.isIsFeatured() == true).collect(Collectors.toList());
        m.addAttribute("cars", cars);
        HashMap<Integer,CarModel> models = new HashMap<>();
        cars.forEach(x -> {
            models.put(x.getId(), carModelDaoDB.getCarModelById(x.getModelID()));
        });
        m.addAttribute("models", models);
        return "index";
    }
    @GetMapping("home/specials")
    public String getAdminSpecialPage(Model model) {
        
        List<Special> specialList = specialService.getAllSpecials();
        model.addAttribute("specialList", specialList);
        
        return "homeSpecials";
    }
    
}
