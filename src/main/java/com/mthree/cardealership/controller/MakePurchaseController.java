/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.dao.TransactionDao;
import com.mthree.cardealership.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ychen
 */
@Controller
public class MakePurchaseController {
    
    @Autowired
    CarDao carDao;
    
    @Autowired
    TransactionDao transactionDao;
    
    @GetMapping("sales/makepurchase/{id}")
    public String getMakePurchasePage(@PathVariable(value="id") int id, Model model) {
        
        Car curCar = carDao.getCarById(id);
        model.addAttribute("car", curCar);
        
        return "makePurchase";
    }
    
    
}
