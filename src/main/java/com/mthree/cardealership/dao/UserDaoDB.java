/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Role;
import com.mthree.cardealership.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author conno
 */
@Repository
public class UserDaoDB implements UserDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public String getUserEmailById(int id) {
        try {
            final String SELECT_EMAIL_BY_ID = "SELECT email FROM users WHERE id = ?";
            String email = jdbc.queryForObject(SELECT_EMAIL_BY_ID, String.class, id);
            return email;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USER = "SELECT * FROM users JOIN user_roles ur ON ur.userId = users.Id;";
        List<User> users = jdbc.query(SELECT_ALL_USER, new UserMapper());
        User user = new User();
        Set<Role> roles = new HashSet<Role>();
        if(users.size() > 1){
            user = users.get(0);
            for(User u : users){
                roles.add(u.getRoles().ifPresent(stream().findFirst()));
            }
        }
        return users;
    }
    
    @Override
    public User getUserById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM users JOIN user_roles ur ON ur.userId = users.Id WHERE id = ?";
            return jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public User addUser(User user) {
        


        final String INSERT_USER = "INSERT INTO users(username, password, email, firstname, lastname) "
                + "VALUES(?,?,?,?,?)";
        final String INSERT_USER_ROLE = "INSERT INTO user_roles(userid, roleid) VALUES(?,?)";
        jdbc.update(INSERT_USER,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName());

        long newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Set<Role> roles = user.getRoles();
        for(Role r: roles)
        jdbc.update(INSERT_USER_ROLE, newId, r.getId());
        user.setId(newId);
        return user;
    }
    
    @Override
    @Transactional
    public void deleteUserById(int id) {
        final String DELETE_USER_STUDENT = "DELETE FROM users WHERE id = ?";
        jdbc.update(DELETE_USER_STUDENT, id);

    }
    
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            
            Set<Role> roles = new HashSet<Role>();
            Role role = new Role();
            if(rs.getInt("roleid") == 2){
                role.setId(1);
                role.setRole("Admin");
            }
            else if(rs.getInt("roleid") == 1){
                role.setId(2);
                role.setRole("Sales");
            }
            roles.add(role);
            User user = new User();
            user.setId(rs.getLong("id"));  
            user.setRoles(roles);
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            return user;
        }
    }
    
}
