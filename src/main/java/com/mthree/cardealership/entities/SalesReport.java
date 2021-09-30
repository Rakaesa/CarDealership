package com.mthree.cardealership.entities;

import java.sql.Date;

public class SalesReport {
    private int id;
    private String name;
    private double totalSales;
    private double totalVehicles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(double totalVehicles) {
        this.totalVehicles = totalVehicles;
    }
}
