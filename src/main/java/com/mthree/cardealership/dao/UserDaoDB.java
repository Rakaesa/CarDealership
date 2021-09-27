/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author conno
 */
@Repository
public class UserDaoDB implements UserDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User getUserEmailById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT username, email FROM users WHERE id = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserDaoDB.UserMapper(), id);
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
    
}
