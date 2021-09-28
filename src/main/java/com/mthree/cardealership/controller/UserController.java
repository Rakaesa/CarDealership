/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.Role;
import com.mthree.cardealership.entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        System.out.println(users.size());
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
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        String username = "placeholder";
        String password = passwordEncoder.encode(request.getParameter("password"));
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");
        
        user.setUsername(username);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);
        
        Set<Role> roles = new HashSet<Role>();
        Role role = new Role();
        if(request.getParameter("role").equals("Admin")){
            role.setId(2);
            role.setRole("Admin");
        }
        else if(request.getParameter("role").equals("Sales")){
            role.setId(1);
            role.setRole("Sales");
        }
        roles.add(role);
        user.setRoles(roles);
        
        userDao.addUser(user);

        return "redirect:/admin/users";        
    }
    
}
