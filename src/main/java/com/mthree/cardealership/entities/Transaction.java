/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.entities;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author conno
 */
public class Transaction {
    
    private int id;
    @NotBlank(message = "User ID must not be blank")
    
    private User user;
    private Car car;
    
    @NotBlank(message = "Purchase Price must not be blank")
    private int purchasePrice;
    
    @NotBlank(message = "Purchase Type must not be blank")
    private String purchaseType;
    
    @NotBlank(message = "Name must not be blank")
    private String name;
    
    @NotBlank(message = "Email must not be blank")
    private String email;
    
    private String phone;
    
    @NotBlank(message = "Street must not be blank")
    @Size(max = 50, message = "Street cannot be more than 50 characters")
    private String streetMain;
    
    private String streetAlt;
    
    @NotBlank(message = "City must not be blank")
    @Size(max = 15, message = "City cannot be more than 15 characters")
    private String city;
    
    @NotBlank(message = "State must not be blank")
    private String state;
    
    @NotBlank(message = "Zipcode must not be blank")
    @Size(max = 10, message="Zipcode cannot be more than 10 characters")
    private String zipcode;
    
    private LocalDate purchaseDate;
    private int userId;
    private int carId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreetMain() {
        return streetMain;
    }

    public void setStreetMain(String streetMain) {
        this.streetMain = streetMain;
    }

    public String getStreetAlt() {
        return streetAlt;
    }

    public void setStreetAlt(String streetAlt) {
        this.streetAlt = streetAlt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }    

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
    
    
}
