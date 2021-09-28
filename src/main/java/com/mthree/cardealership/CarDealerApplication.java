package com.mthree.cardealership;

import com.mthree.cardealership.controller.CarController;
import com.mthree.cardealership.controller.ContactController;
import com.mthree.cardealership.controller.TransactionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class CarDealerApplication {

    @Autowired
    CarController carController;
    
    @Autowired
    ContactController contactController;
   
    
	public static void main(String[] args) {
		SpringApplication.run(CarDealerApplication.class, args);
	}

}
