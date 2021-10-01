/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.dao.TransactionDao;
import com.mthree.cardealership.entities.Car;
import com.mthree.cardealership.entities.CarModel;
import com.mthree.cardealership.service.CarModelService;
import com.mthree.cardealership.service.MakeService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ychen
 */
@Controller
public class MakePurchaseController {
    
    @Autowired
    CarDao carDao;
    
    @Autowired
    MakeService makeService;
    
    @Autowired
    CarModelService carModelService;
    
    @Autowired
    TransactionDao transactionDao;
    
    @GetMapping("sales/makepurchase/{id}")
    public String getMakePurchasePage(@PathVariable(value="id") int id, Model model) {
        
        Car curCar = carDao.getCarById(id);
        CarModel curModel = carModelService.getCarModelById(curCar.getModelID());
        String makeName = makeService.getMakeNameById(curModel.getMakeID());
        String modelName = curModel.getModel();
        
        model.addAttribute("car", curCar);
        model.addAttribute("makeName", makeName);
        model.addAttribute("modelName", modelName);
        
        System.out.println("isPurchased: " + curCar.isIsPurchased());
        
        return "makePurchase";
    }
    
    @PostMapping("sales/makepurchase/addTransaction")
    public String addTransaction(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("carId"));
        Car curCar = carDao.getCarById(carId);
        
        //carDao.markCarPurchased(curCar);
        
        return "redirect:/sales/makepurchase/" + carId;
    }
    
    
}
