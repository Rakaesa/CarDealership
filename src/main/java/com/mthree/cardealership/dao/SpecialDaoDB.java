/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Special;
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
public class SpecialDaoDB implements SpecialDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Special> getAllSpecials() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final String SELECT_ALL_SPECIAL = "SELECT * FROM specials";
        List<Special> specials = jdbc.query(SELECT_ALL_SPECIAL, new SpecialMapper());
        return specials;
    
    }

    @Override
    @Transactional
    public Special addSpecial(Special special) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        final String INSERT_SPECIAL = "INSERT INTO specials(title, description) "
                + "VALUES(?, ?)";
        jdbc.update(INSERT_SPECIAL, 
                special.getTitle(),
                special.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setId(newId);
        
        return special;
    
    }

    @Override
    public Special getSpecialById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        try {
            final String SELECT_SPECIAL = "SELECT * FROM specials WHERE id = ?";
            Special special = jdbc.queryForObject(SELECT_SPECIAL, new SpecialMapper(), id);
            return special;
            
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteSpecialById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        final String DELETE_SPECIAL = "DELETE FROM specials WHERE id = ?";
        jdbc.update(DELETE_SPECIAL, id);
    
    }
    
    public static final class SpecialMapper implements RowMapper<Special> {

        @Override
        public Special mapRow(ResultSet rs, int rowNum) throws SQLException {
            Special special = new Special();
            special.setId(rs.getInt("id"));
            special.setTitle(rs.getString("title"));
            special.setDescription(rs.getString("description"));
            
            return special;
        }
        
    }
    
}
