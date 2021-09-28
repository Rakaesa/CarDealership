package com.mthree.cardealership.entities;

import java.util.Objects;

/**
 *
 * @author me
 */
public class Car {

    public Car(){
    
    }
    public Car(int modelID, int year, String type, double msrp, double price, String vin, String interiorColor, String transmission, String color, String bodyStyle, String description, boolean isFeatured) {
        this.modelID = modelID;
        this.year = year;
        this.type = type;
        this.msrp = msrp;
        this.price = price;
        this.vin = vin;
        this.interiorColor = interiorColor;
        this.transmission = transmission;
        this.color = color;
        this.bodyStyle = bodyStyle;
        this.description = description;
        this.isFeatured = isFeatured;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + this.modelID;
        hash = 23 * hash + this.year;
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.msrp) ^ (Double.doubleToLongBits(this.msrp) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.vin);
        hash = 23 * hash + Objects.hashCode(this.interiorColor);
        hash = 23 * hash + Objects.hashCode(this.transmission);
        hash = 23 * hash + Objects.hashCode(this.color);
        hash = 23 * hash + Objects.hashCode(this.bodyStyle);
        hash = 23 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.modelID != other.modelID) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (Double.doubleToLongBits(this.msrp) != Double.doubleToLongBits(other.msrp)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.interiorColor, other.interiorColor)) {
            return false;
        }
        if (!Objects.equals(this.transmission, other.transmission)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    private int id;
    private int modelID;
    private int year;
    private String type;
    private double msrp;
    private double price;
    private String vin;
    private String interiorColor;
    private String transmission;
    private String color;
    private String bodyStyle;
    private String description;
    private boolean isFeatured;
    
}
