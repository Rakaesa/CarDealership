/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Car;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author me
 */
@SpringBootTest 
public class CarDaoDBTest {
    
    
    @Autowired
    CarDaoDB dao;
    
    public CarDaoDBTest() {
    }

    /**
     * Test of getCarById method, of class CarDaoDB.
     */
    @Test
    public void testGetCarById() {
        int idToSearchFor = 1;
        System.out.println(dao);
        Car c = dao.getCarById(idToSearchFor);
        assertTrue(c.getId() == idToSearchFor, "What we get should have a matching ID");
    }

    /**
     * Test of getAllCars method, of class CarDaoDB.
     */
    @Test
    public void testGetAllCars() {
        List<Car> all = dao.getAllCars();
        assertTrue(all.size() > 0, "we should have cars");
        assertTrue(all.get(0).getClass().toString().contains("Car"), "Car should be the class of the objects in the array.");
    }

    /**
     * Test of addCar method, of class CarDaoDB.
     */
    @Test
    public void testAddCar() {
        List<Car> all = dao.getAllCars();
        int initialSize = all.size();
        Car c = dao.getCarById(1);
        dao.addCar(c);
        List<Car> allPlusOne = dao.getAllCars();
        assertTrue(initialSize == allPlusOne.size() - 1);
    }

    /**
     * Test of deleteCarById method, of class CarDaoDB.
     */
    @Test
    public void testDeleteCarById() {
        List<Car> all = dao.getAllCars();
//        all.stream().max(x -> x.)
    }

    /**
     * Test of updateCarByID method, of class CarDaoDB.
     */
    @Test
    public void testUpdateCarByID() {
    }
    
}
