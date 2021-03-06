/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.entities.Special;
import com.mthree.cardealership.service.SpecialService;
import java.util.List;
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
public class adminSpecialContoller {
    
    @Autowired
    SpecialService specialService;
    
    @GetMapping("admin/specials")
    public String getAdminSpecialPage(Model model) {
        
        List<Special> specialList = specialService.getAllSpecials();
        model.addAttribute("specialList", specialList);
        
        return "adminSpecials";
    }
    
    @PostMapping("admin/addSpecial")
    public String addNewSpecial(HttpServletRequest request) {
        
        String title = request.getParameter("specialTitle");
        String description = request.getParameter("specialDescription");
        
        Special newSpecial = new Special();
        newSpecial.setTitle(title);
        newSpecial.setDescription(description);
        specialService.addSpecial(newSpecial);
       
        return "redirect:/admin/specials";
    }
    
    @GetMapping("admin/deleteSpecial")
    public String deleteSpecial(Integer specialId) {
        
        //int id = Integer.parseInt(request.getParameter("specialId"));
        specialService.deleteSpecialById(specialId);
        
        return "redirect:/admin/specials";
    }
    
}
