/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.service;

import com.mthree.cardealership.dao.CarModelDao;
import com.mthree.cardealership.entities.CarModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ychen
 */
@Service
public class CarModelService {
    
    @Autowired
    CarModelDao carModelDao;
    
    public CarModel getCarModelById(int id) {
        return carModelDao.getCarModelById(id);
    }
    
    public List<CarModel> getAllCarModels() {
        return carModelDao.getAllCarModels();
    }
    
    public CarModel addCarModel(CarModel carModel, String makeName) {
        
        CarModel fromDao = carModelDao.getCarModelByNameAndMake(carModel.getModel(), makeName);
        if (fromDao == null) {
            return carModelDao.addCarModel(carModel);
        } else {
            return fromDao;
        }
    }
    
    public void deleteCarModelById(int id) {
        carModelDao.deleteCarModelById(id);
    }
}
