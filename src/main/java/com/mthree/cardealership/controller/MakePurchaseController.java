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
import com.mthree.cardealership.entities.Transaction;
import com.mthree.cardealership.entities.User;
import com.mthree.cardealership.service.CarModelService;
import com.mthree.cardealership.service.DealershipUserDetails;
import com.mthree.cardealership.service.MakeService;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        
        int purchasePrice = Integer.parseInt(request.getParameter("purchasePrice"));
        String purchaseType = request.getParameter("purchaseType");
        String name = request.getParameter("clientName");
        String email = request.getParameter("email");
        String phone = request.getParameter("clientPhone");
        String street1 = request.getParameter("street1");
        String street2 = request.getParameter("street2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        
        Transaction newTransaction = new Transaction();
        newTransaction.setUserId(getUserid());
        newTransaction.setCarId(carId);
        newTransaction.setPurchaseDate(LocalDate.now());
        newTransaction.setPurchasePrice(purchasePrice);
        newTransaction.setPurchaseType(purchaseType);
        newTransaction.setName(name);
        newTransaction.setEmail(email);
        newTransaction.setPhone(phone);
        newTransaction.setStreetMain(street1);
        newTransaction.setStreetAlt(street2);
        newTransaction.setCity(city);
        newTransaction.setState(state);
        newTransaction.setZipcode(zipcode);
        
        try {
            transactionDao.addTransaction(newTransaction);
            carDao.markCarPurchased(curCar);
        } catch(Exception e) {
            System.out.println(e);
        }
        
        
        return "redirect:/sales/makepurchase/" + carId;
    }
    
    public Integer getUserid(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((DealershipUserDetails) auth.getPrincipal()).getUser();
        return Math.toIntExact(user.getId());
    }
    
    
}
