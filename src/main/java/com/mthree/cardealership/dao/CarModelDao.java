/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.CarModel;
import java.util.List;

/**
 *
 * @author ychen
 */
public interface CarModelDao {
    
    CarModel getCarModelById(int id);
    List<CarModel> getAllCarModels();
    CarModel addCarModel(CarModel carModel);
    void deleteCarModelById(int id);
    
}
