/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Car;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author conno
 */
@Repository
public class CarDaoDB implements CarDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Car getCarById(int id) {
        try {
            final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
            Car car = jdbc.queryForObject(SELECT_CAR_BY_ID, new CarMapper(), id);
            return car;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Car> getAllCars() {
        final String SELECT_ALL_CARS = "SELECT car.id as id, year, type, mrsp, price, vin, interior, trans, color, bodystyle," +
" description, featured, purchased, make, model, modelid from cardealership.car car" +
" JOIN cardealership.model mdl on car.modelid = mdl.id" +
" JOIN cardealership.make on mdl.makeid = cardealership.make.id";
        
        List<Car> cars = jdbc.query(SELECT_ALL_CARS, new CarMapper());
        return cars;
    }

    @Override
    public Car addCar(Car c) {
                /*
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
        */
        final String INSERT_CAR = "INSERT INTO car(modelID, year, type, mrsp, price, vin, interior, trans, color, bodyStyle,description, featured, purchased) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_CAR,
                c.getModelID(), c.getYear(), c.getType(), c.getMsrp(), c.getPrice(), c.getVin(), c.getInteriorColor(), c.getTransmission(), c.getColor(), c.getBodyStyle(), c.getDescription(), c.isIsFeatured(),c.isIsPurchased());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        c.setId(newId);
        return c;
    }

    @Override
    public void deleteCarById(int id) {
        final String DELETE_CAR = "DELETE FROM car WHERE id = ?";
        jdbc.update(DELETE_CAR, id);
    }
    
    public void updateCarByID(int id, String modelID, String year, String type, String mrsp, String price,String vin, String interior,String trans, String color, String bodyStyle,String description, String featured ){
        if(featured.equalsIgnoreCase("true")){
            featured = "1";
        } else {
            featured = "0";
        }
        final String UPDATE_CAR = "UPDATE CAR SET modelID = ?, "
                + "year = ? , type = ? , mrsp = ? , price = ? , vin = ? , interior = ? , trans = ? , color = ? , bodyStyle = ? ,description = ? , featured  = ?  WHERE ID = ? ";
        jdbc.update(UPDATE_CAR, modelID, year, type, mrsp, price, vin, interior, trans, color, bodyStyle, description, featured, id);
    }
    
    @Override
    public void markCarPurchased(Car car) {
        
        final String Mark_Purchased = "UPDATE car SET purchased = true where id = ?";
        jdbc.update(Mark_Purchased, car.getId());
        
    }
    
    public static final class CarMapper implements RowMapper<Car> {

        
        /*
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
        */
        @Override
        public Car mapRow(ResultSet rs, int index) throws SQLException {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setModelID(rs.getInt("modelID"));
            car.setYear(rs.getInt("year"));
            car.setType(rs.getString("type"));
            car.setMsrp(rs.getDouble("mrsp"));
            car.setPrice(rs.getDouble("price"));
            car.setVin(rs.getString("vin"));
            car.setInteriorColor(rs.getString("interior"));
            car.setTransmission(rs.getString("trans"));
            car.setColor(rs.getString("color"));
            car.setBodyStyle(rs.getString("bodyStyle"));
            car.setDescription(rs.getString("description"));
            car.setIsFeatured(rs.getBoolean("featured"));
            car.setIsPurchased(rs.getBoolean("purchased"));
            return car;
        }
    }
    
}
