/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.ContactDao;
import com.mthree.cardealership.entities.Contact;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author conno
 */
@Controller
public class ContactController {
    
    @Autowired
    ContactDao contactDao;

    @GetMapping("home/contactUs")
    public String contactUs(Model model) {
        return "contactUs";
    }

    @PostMapping("home/contactUs")
    public String addContact(@Valid Contact contact, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("flag", "Error");
            return "contactUs";
        }
        contactDao.addContact(contact);
        model.addAttribute("success","Thank you! We'll get back to you as soon as possible.");
        return "contactUs";
    }

    @GetMapping("contactDetail")
    public String contactDetail(Integer id, Model model) {
        Contact contact = contactDao.getContactById(id);
        model.addAttribute("contact", contact);
        return "admin/contactDetail";
    }

    @GetMapping("deleteContact")
    public String deleteContact(Integer id) {
        contactDao.deleteContactById(id);
        return "redirect:/admin/contacts";
    }
}
