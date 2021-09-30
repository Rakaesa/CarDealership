/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class ContactDaoDB implements ContactDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Contact getContactById(int id) {
        try {
            final String SELECT_CONTACT_BY_ID = "SELECT * FROM contact WHERE id = ?";
            Contact contact = jdbc.queryForObject(SELECT_CONTACT_BY_ID, new ContactMapper(), id);
            return contact;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        final String SELECT_ALL_CONTACTS = "SELECT * FROM contact";
        List<Contact> contacts = jdbc.query(SELECT_ALL_CONTACTS, new ContactMapper());
        return contacts;
    }

    @Override
    public Contact addContact(Contact contact) {
        final String INSERT_CONTACT = "INSERT INTO contact(name, email, phone, message) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_CONTACT,
                contact.getName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getMessage());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setId(newId);
        return contact;
    }

    @Override
    public void deleteContactById(int id) {
        final String DELETE_CONTACT = "DELETE FROM contact WHERE id = ?";
        jdbc.update(DELETE_CONTACT, id);
    }
    
    public static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int index) throws SQLException {
            Contact contact = new Contact();
            contact.setId(rs.getInt("id"));
            contact.setEmail(rs.getString("email"));
            contact.setPhone(rs.getString("phone"));
            contact.setMessage(rs.getString("message"));
            return contact;
        }
    }
    
}
