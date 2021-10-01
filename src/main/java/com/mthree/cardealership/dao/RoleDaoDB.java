/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoDB implements RoleDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    
    @Override
    public List<Role> getAllRoles() {
        final String SELECT_ALL_ROLE = "SELECT * FROM roles;";
        List<Role> roles = jdbc.query(SELECT_ALL_ROLE, new RoleMapper());
        return roles;
    }
    
    @Override
    public Role getRoleByName(String name){
        final String SELECT_ROLE = "SELECT * FROM roles WHERE role = ?;";
        return jdbc.queryForObject(SELECT_ROLE, new RoleMapper(), name);        
    }
    
    @Override
     public Role getRoleById(int id){
         
         final String SELECT_ROLE = "SELECT * FROM roles WHERE id = ?;";
         return jdbc.queryForObject(SELECT_ROLE, new RoleMapper(), id);
         
     } 
    
     public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int index) throws SQLException {
            
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));
            return role;
        }
    }   
}
