/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.Make;
import com.mthree.cardealership.entities.User;
import com.mthree.cardealership.service.DealershipUserDetails;
import com.mthree.cardealership.service.MakeService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ychen
 */
@Controller
public class MakeController {
    
    @Autowired
    MakeService makeService;
    
    @Autowired
    UserDao userDao;
    
    @GetMapping("admin/makes")
    public String getMakePage(Model model) {
        
        List<Make> makes = makeService.getAllMakes();
        List<MakeWithEmail> makeWithEmails = new ArrayList<>();
        for (Make aMake : makes) {
            String userEmail = userDao.getUserEmailById(aMake.getUserID());
            MakeWithEmail data = new MakeWithEmail(aMake.getMake(), 
                    aMake.getDateAdded().toString(), 
                    userEmail);
            makeWithEmails.add(data);
        }
        model.addAttribute("makes", makeWithEmails);
        
        return "makes";
        
    }
    
    @PostMapping("admin/makes")
    public String addMake(HttpServletRequest request) {
        String makeName = request.getParameter("makeName");
        LocalDate dateAdded = LocalDate.now();
        Make newMake = new Make();
        newMake.setMake(makeName);
        newMake.setDateAdded(dateAdded);
        newMake.setUserID(getUserid());
        
        makeService.addMake(newMake);
        
        return "redirect:/admin/makes";
    }
    
    public Integer getUserid(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((DealershipUserDetails) auth.getPrincipal()).getUser();
        return Math.toIntExact(user.getId());
    }
    
    
    public class MakeWithEmail {
        
        private String name;
        private String dateAdded;
        private String userEmail;

        public MakeWithEmail(String name, String dateAdded, String userEmail) {
            this.name = name;
            this.dateAdded = dateAdded;
            this.userEmail = userEmail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }
        
        
        
    }
    
    
    
}
