/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Car;
import java.util.List;

/**
 *
 * @author me
 */
public interface CarDao {
    Car getCarById(int id);
    List<Car> getAllCars();
    Car addCar(Car c);
    void deleteCarById(int id);
    void markCarPurchased(Car car);
}
