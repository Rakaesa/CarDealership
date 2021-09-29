/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.RoleDao;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.Role;
import com.mthree.cardealership.entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

    @Autowired
    RoleDao roleDao;

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
        List<Role> roles = roleDao.getAllRoles();
        model.addAttribute("roles", roles);
        String[] userRoles = new String[user.getRoles().size()];
        String[] roleStr = new String[roles.size()];
        int index = 0;
        for (Role r : user.getRoles()) {
            userRoles[index] = r.getRole();
            index++;
        }
        index = 0;
        model.addAttribute("userRoles", userRoles);
        for (Role r : roles) {
            roleStr[index] = r.getRole();
            index++;
        }
        model.addAttribute("roleStr", roleStr);
        return "edituser";
    }

    @PostMapping("admin/editUser")
    public String editUserPost(User user, BindingResult result, HttpServletRequest request, Model model) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String username = request.getParameter("username");
        String password = passwordEncoder.encode(request.getParameter("password"));
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");

        String[] rolesStr = request.getParameterValues("role");

        user.setUsername(username);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);

        Set<Role> roles = new HashSet<Role>();
        try {
            for (String r : rolesStr) {
                roles.add(roleDao.getRoleByName(r));
            }
        }
        catch (NullPointerException e) {
            result.rejectValue("roles", "noRoleTaken", "A role must be selected.");
            return addUser(user, result, request, model);
        }
        user.setRoles(roles);

        try {
            userDao.editUser(user);
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("username", "usernameInUse", "Username is already taken.");
            return addUser(user, result, request, model);
        }

        return "redirect:/admin/users";
    }

    @GetMapping("admin/adduser")
    public String addUserGet(Model model) {
        List<Role> roles = roleDao.getAllRoles();
        model.addAttribute("roles", roles);
        return "adduser";
    }

    @PostMapping("admin/adduser")
    public String addUser(@Valid User user, BindingResult result, HttpServletRequest request, Model model) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String username = request.getParameter("username");
        String password = passwordEncoder.encode(request.getParameter("password"));
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");

        String[] rolesStr = request.getParameterValues("role");

        user.setUsername(username);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);

        if (result.hasErrors()) {
            List<Role> roles = roleDao.getAllRoles();
            model.addAttribute("roles", roles);
            model.addAttribute("flag", "Error");
            model.addAttribute("user", user);
            return "adduser";
        }
        Set<Role> roles = new HashSet<Role>();
        try {
            for (String r : rolesStr) {
                roles.add(roleDao.getRoleByName(r));
            }
        }
        catch (NullPointerException e) {
            result.rejectValue("roles", "noRoleTaken", "A role must be selected.");
            return addUser(user, result, request, model);
        }
        user.setRoles(roles);
        try {
            userDao.addUser(user);
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("username", "usernameInUse", "Username is already taken.");
            return addUser(user, result, request, model);
        }

        return "redirect:/admin/users";
    }

}
