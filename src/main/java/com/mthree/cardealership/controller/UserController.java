/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author conno
 */
@Controller
public class UserController {
    
    @Autowired
    UserDao userDao;
    
    @GetMapping("admin/users")
    public String displayUsers(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    @GetMapping("admin/editUser")
    public String editUser(Integer id, Model model) {
        User user = userDao.getUserById(id);
        model.addAttribute("user", user);
        return "edituser";
    }
    
    @GetMapping("admin/adduser")
    public String addUserGet(){
        
        return "adduser";
    }
    
    @PostMapping("admin/adduser")
    public String addUser(User user, HttpServletRequest request){
        
        userDao.addUser(user);

        return "redirect:/users";        
    }
    
}
