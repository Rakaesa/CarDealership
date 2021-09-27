/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.entities;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author conno
 */
public class Contact {
    
    private int id;
    @NotBlank(message = "Message must not be blank")
    @Size(max = 255, message = "Description must be fewer than 255 characters")
    private String message;
    
    //AT LEAST One of the following fields must be used.
    
    @NotBlank(message = "Email must not be blank")
    private String email;
    
    @NotBlank(message = "Phone must not be blank")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
    
    
    
}
