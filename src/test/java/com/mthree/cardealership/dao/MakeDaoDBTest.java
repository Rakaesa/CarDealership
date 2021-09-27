/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Make;
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
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of addMake method, of class MakeDaoDB.
     */
    @Test
    public void testAddAndGetMake() {
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(123);
        makeDao.addMake(make);
        
        Make fromDao = makeDao.getMakeById(make.getId());
        assertEquals(make, fromDao);
        
    }
    

    /**
     * Test of getAllMakes method, of class MakeDaoDB.
     */
    @Test
    public void testGetAllMakes() {
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(123);
        makeDao.addMake(make);
        
        Make make2 = new Make();
        make2.setMake("Fiat");
        make2.setDateAdded(LocalDate.now());
        make2.setUserID(456);
        makeDao.addMake(make2);
        
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
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(123);
        makeDao.addMake(make);
        
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
        
        Make make = new Make();
        make.setMake("Bentley");
        make.setDateAdded(LocalDate.now());
        make.setUserID(123);
        makeDao.addMake(make);
        
        String fromDao = makeDao.getMakeNameById(make.getId());
        assertEquals(make.getMake(), fromDao);
        
    }
    
}
