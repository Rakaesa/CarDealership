/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.CarModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author ychen
 */
@Repository
public class CarModelDaoDB implements CarModelDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public CarModel addCarModel(CarModel carModel) {
        final String INSERT_CARMODEL = "INSERT INTO model(model, makeid, userid, dateAdded) "
                + "VALUES(?, ?, ?, ?)";
        jdbc.update(INSERT_CARMODEL,
                carModel.getModel(),
                carModel.getMakeID(),
                carModel.getUserID(),
                carModel.getDateAdded());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        carModel.setId(newId);
        
        return carModel;
    }

    @Override
    public CarModel getCarModelById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
        try {
            
            final String SELECT_CARMODEL = "SELECT * FROM model WHERE id = ?";
            CarModel carModel = jdbc.queryForObject(SELECT_CARMODEL, new CarModelMapper(), id);
            return carModel;

        } catch(DataAccessException ex) {
            return null;
        }
    
    }

    @Override
    public List<CarModel> getAllCarModels() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final String SELECT_ALL_CARMODEL = "SELECT * FROM model";
        List<CarModel> carModels = jdbc.query(SELECT_ALL_CARMODEL, new CarModelMapper());
        return carModels;
    }

    @Override
    @Transactional
    public void deleteCarModelById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final String DELETE_CARMODEL = "DELETE FROM model WHERE id = ?";
        jdbc.update(DELETE_CARMODEL, id);
    }
    
    public static final class CarModelMapper implements RowMapper<CarModel> {
        
        @Override
        public CarModel mapRow(ResultSet rs, int index) throws SQLException {
            
            CarModel carModel = new CarModel();
            carModel.setId(rs.getInt("id"));
            carModel.setModel(rs.getString("model"));
            carModel.setMakeID(rs.getInt("makeid"));
            carModel.setUserID(rs.getInt("userid"));
            carModel.setDateAdded(rs.getDate("dateAdded").toLocalDate());
            
            return carModel;
        }
        
        
    }
    
    
    
    
}
