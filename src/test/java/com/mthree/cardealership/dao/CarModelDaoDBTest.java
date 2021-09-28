/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.CarModel;
import com.mthree.cardealership.entities.Make;
import com.mthree.cardealership.entities.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ychen
 */
@SpringBootTest
public class CarModelDaoDBTest {
    
    @Autowired
    CarModelDao carModelDao;
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    UserDao userDao;
    
    public CarModelDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        List<CarModel> carModels = carModelDao.getAllCarModels();
        for(CarModel carModel : carModels) {
            carModelDao.deleteCarModelById(carModel.getId());
        }
        
        List<Make> makes = makeDao.getAllMakes();
        for(Make make : makes) {
            makeDao.deleteMakeById(make.getId());
        }
        
        List<User> users = userDao.getAllUsers();
        for(User user : users) {
            userDao.deleteUserById(Math.toIntExact(user.getId()));
        }
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addCarModel method, of class CarModelDaoDB.
     */
    @Test
    public void testAddAndGetCarModel() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        userDao.addUser(user);
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        makeDao.addMake(make);
        
        CarModel carModel = new CarModel();
        carModel.setMakeID(make.getId());
        carModel.setModel("Bentayga");
        carModel.setUserID(Math.toIntExact(user.getId()));
        carModel.setDateAdded(LocalDate.now());
        carModelDao.addCarModel(carModel);
        
        CarModel fromDao = carModelDao.getCarModelById(carModel.getId());
        assertEquals(carModel, fromDao);
        
    }


    /**
     * Test of getAllCarModels method, of class CarModelDaoDB.
     */
    @Test
    public void testGetAllCarModels() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        userDao.addUser(user);
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        makeDao.addMake(make);
        
        CarModel carModel = new CarModel();
        carModel.setMakeID(make.getId());
        carModel.setModel("Bentayga");
        carModel.setUserID(Math.toIntExact(user.getId()));
        carModel.setDateAdded(LocalDate.now());
        carModelDao.addCarModel(carModel);
        
        CarModel carModel2 = new CarModel();
        carModel2.setMakeID(make.getId());
        carModel2.setModel("Continental GT");
        carModel2.setUserID(Math.toIntExact(user.getId()));
        carModel2.setDateAdded(LocalDate.now());
        carModelDao.addCarModel(carModel2);
        
        List<CarModel> carModels = carModelDao.getAllCarModels();
        
        assertEquals(2, carModels.size());
        assertTrue(carModels.contains(carModel));
        assertTrue(carModels.contains(carModel2));
        
    }

    /**
     * Test of deleteCarModelById method, of class CarModelDaoDB.
     */
    @Test
    public void testDeleteCarModelById() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        userDao.addUser(user);
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        makeDao.addMake(make);
        
        CarModel carModel = new CarModel();
        carModel.setMakeID(make.getId());
        carModel.setModel("Bentayga");
        carModel.setUserID(Math.toIntExact(user.getId()));
        carModel.setDateAdded(LocalDate.now());
        carModelDao.addCarModel(carModel);
        
        CarModel fromDao = carModelDao.getCarModelById(carModel.getId());
        assertEquals(carModel, fromDao);
        
        carModelDao.deleteCarModelById(carModel.getId());
        
        fromDao = carModelDao.getCarModelById(carModel.getId());
        assertNull(fromDao);
        
        
    }
    
}
