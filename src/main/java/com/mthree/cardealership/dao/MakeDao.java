/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Make;
import java.util.List;

/**
 *
 * @author ychen
 */
public interface MakeDao {
    
    List<Make> getAllMakes();
    Make addMake(Make make);
    String getMakeNameById(int id);
    Make getMakeById(int id);
    void deleteMakeById(int id);
    Make getMakeByName(String name);
    
}
