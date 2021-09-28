/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.User;
import java.util.List;

/**
 *
 * @author conno
 */
public interface UserDao {
    
    User getUserEmailById(int id);
    
    List<User> getAllUsers();
    
}
