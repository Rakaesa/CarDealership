/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Contact;
import java.util.List;

/**
 *
 * @author conno
 */
public interface ContactDao {
    
    Contact getContactById(int id);
    List<Contact> getAllContacts();
    Contact addContact(Contact contact);
    void deleteContactById(int id);
    
}
