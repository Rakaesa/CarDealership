/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Car;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
        int initSize = all.size();
        Car toBeDeleted = all.get(all.size() - 1);
        dao.deleteCarById(toBeDeleted.getId());
        int afterSize = dao.getAllCars().size();
        assertTrue(initSize == afterSize + 1);
    }

    /**
     * Test of updateCarByID method, of class CarDaoDB.
     */
    @Test
    public void testUpdateCarByID() {
        List<Car> all = dao.getAllCars();
        int initSize = all.size();
        Car toBeUpdated = all.get(all.size() - 1);
        //String modelID, String year, String type, String mrsp, String price,
        //String vin, String interior,String trans, String color, 
        //String bodyStyle,String description, String 
        String initialVin = toBeUpdated.getVin();
        String vinToGive = generateRandomString().substring(0, 17);
        
        dao.updateCarByID(toBeUpdated.getId(),String.valueOf(toBeUpdated.getModelID()), String.valueOf(toBeUpdated.getYear()), toBeUpdated.getType(), String.valueOf(toBeUpdated.getMsrp()),
                String.valueOf(toBeUpdated.getPrice()), vinToGive, toBeUpdated.getInteriorColor(), toBeUpdated.getTransmission(), toBeUpdated.getColor() , toBeUpdated.getBodyStyle(), toBeUpdated.getDescription(), String.valueOf(toBeUpdated.isIsFeatured()));
        Car carById = dao.getCarById(toBeUpdated.getId());
        assertEquals(carById.getVin(), vinToGive);
    }
    
    public String generateRandomString(){
        byte[] array = new byte[100]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}
