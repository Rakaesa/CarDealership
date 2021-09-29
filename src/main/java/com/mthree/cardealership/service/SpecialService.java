/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.service;

import com.mthree.cardealership.dao.SpecialDao;
import com.mthree.cardealership.entities.Special;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ychen
 */
@Service
public class SpecialService {
    
    @Autowired
    SpecialDao specialDao;
    
    public List<Special> getAllSpecials() {
        return specialDao.getAllSpecials();
    }
    
    public Special addSpecial(Special special) {
        return specialDao.addSpecial(special);
    }
    
    public Special getSpecialById(int id) {
        return specialDao.getSpecialById(id);
    }
    public void deleteSpecialById(int id) {
        specialDao.deleteSpecialById(id);
    }
    
}
