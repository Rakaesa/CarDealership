/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Make;
import com.mthree.cardealership.entities.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



/**
 *
 * @author ychen
 */
@SpringBootTest
public class MakeDaoDBTest {
    
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    UserDao userDao;
    
    public MakeDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
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
     * Test of addMake method, of class MakeDaoDB.
     */
    @Test
    public void testAddAndGetMake() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        //System.out.println("before assign ID: " + user.getId());
        user.setId(userDao.addUser(user).getId());
        System.out.println(user.getId());
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        make.setId(makeDao.addMake(make).getId());
        
        Make fromDao = makeDao.getMakeById(make.getId());
        assertEquals(make, fromDao);
        
    }
    

    /**
     * Test of getAllMakes method, of class MakeDaoDB.
     */
    @Test
    public void testGetAllMakes() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        user.setId(userDao.addUser(user).getId());
        
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password");
        user2.setEmail("user2@test.com");
        user2.setId(userDao.addUser(user2).getId());
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        make.setId(makeDao.addMake(make).getId());
        
        Make make2 = new Make();
        make2.setMake("Fiat");
        make2.setDateAdded(LocalDate.now());
        make2.setUserID(Math.toIntExact(user2.getId()));
        make2.setId(makeDao.addMake(make2).getId());
        
        List<Make> makes = makeDao.getAllMakes();
        
        assertEquals(2, makes.size());
        assertTrue(makes.contains(make));
        assertTrue(makes.contains(make2));
    }


    /**
     * Test of deleteMakeById method, of class MakeDaoDB.
     */
    @Test
    public void testDeleteMakeById() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        user.setId(userDao.addUser(user).getId());
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        make.setId(makeDao.addMake(make).getId());
        
        Make fromDao = makeDao.getMakeById(make.getId());
        assertEquals(make, fromDao);
        
        makeDao.deleteMakeById(make.getId());
        
        fromDao = makeDao.getMakeById(make.getId());
        assertNull(fromDao);
        
    }

    /**
     * Test of getMakeNameById method, of class MakeDaoDB.
     */
    @Test
    public void testGetMakeNameById() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        user.setId(userDao.addUser(user).getId());
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        make.setId(makeDao.addMake(make).getId());
        
        String fromDao = makeDao.getMakeNameById(make.getId());
        assertEquals(make.getMake(), fromDao);
        
    }
    
    @Test
    public void testGetMakeByName() {
        
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setEmail("user1@test.com");
        user.setId(userDao.addUser(user).getId());
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(Math.toIntExact(user.getId()));
        make.setId(makeDao.addMake(make).getId());
        
        Make fromDao = makeDao.getMakeByName(make.getMake());
        assertEquals(make, fromDao);
        
        Make fromDaoNull = makeDao.getMakeByName("noOne");
        assertNull(fromDaoNull);
        
    }
    
}
