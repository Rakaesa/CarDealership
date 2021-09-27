/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.ContactDao;
import com.mthree.cardealership.entities.Contact;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author conno
 */
public class ContactController {
    
    @Autowired
    ContactDao contactDao;

    @GetMapping("contacts")
    public String displayContacts(Model model) {
        List<Contact> contacts = contactDao.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "admin/contacts";
    }

    @PostMapping("addContact")
    public String addCourse(Contact contact, HttpServletRequest request) {
        contactDao.addContact(contact);
        return "redirect:/admin/contacts";
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
