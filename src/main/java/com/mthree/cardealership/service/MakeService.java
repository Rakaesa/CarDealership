/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.service;

import com.mthree.cardealership.dao.MakeDao;
import com.mthree.cardealership.entities.Make;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ychen
 */
@Service
public class MakeService {
    
    @Autowired
    MakeDao makeDao;
    
    public List<Make> getAllMakes() {
        return makeDao.getAllMakes();
    }
    
    public Make getMakeById(int id) {
        
        try {
            return makeDao.getMakeById(id);

        } catch(DataAccessException ex) {
            return null;
        }     
    }
    
    public Make addMake(Make make) {
        
        Make fromDao = makeDao.getMakeByName(make.getMake());
        
        if (fromDao == null) {
            return makeDao.addMake(make);
        } else {
            return fromDao;
        }
    }
    
    public void deleteMakeById(int id) {
        makeDao.deleteMakeById(id);
    }

    public String getMakeNameById(int id) {
        return makeDao.getMakeNameById(id);
    }
    
    public Make getMakeByName(String name) {
        
        try {
            return makeDao.getMakeByName(name);

        } catch(DataAccessException ex) {
            return null;
        }
        
    }
    
    
}
