/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Contact;
import com.mthree.cardealership.entities.Transaction;
import com.mthree.cardealership.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author conno
 */
public class TransactionDaoDB implements TransactionDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Transaction getTransactionById(int id) {
        try {
            final String SELECT_TRANSACTION_BY_ID = "SELECT * FROM transactions WHERE id = ?";
            Transaction oneTransaction = jdbc.queryForObject(SELECT_TRANSACTION_BY_ID, new TransactionMapper(), id);
            return oneTransaction;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        final String SELECT_ALL_TRANSACTION = "SELECT * FROM transactions";
        List<Transaction> transactions = jdbc.query(SELECT_ALL_TRANSACTION, new TransactionMapper());
        return transactions;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        final String INSERT_CONTACT = "INSERT INTO transactions(purchaseprice, purchasetype, name, email, phone, streeet1, " +
                "street2, city, state, zipcode) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_CONTACT,
                transaction.getPurchasePrice(),
                transaction.getPurchaseType(),
                transaction.getName(),
                transaction.getEmail(),
                transaction.getPhone(),
                transaction.getStreetMain(),
                transaction.getStreetAlt(),
                transaction.getCity(),
                transaction.getState(),
                transaction.getZipcode());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        transaction.setId(newId);
        return transaction;
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        final String UPDATE_TRANSACTION = "UPDATE transactions SET purchaseprice = ?, purchasetype = ?, " +
                "name = ?, email = ?, phone = ?, street1 = ?, street2 = ?, city = ?, state = ?, zipcode = ? WHERE id = ?";
        jdbc.update(UPDATE_TRANSACTION,
                transaction.getPurchasePrice(),
                transaction.getPurchaseType(),
                transaction.getName(),
                transaction.getEmail(),
                transaction.getPhone(),
                transaction.getStreetMain(),
                transaction.getStreetAlt(),
                transaction.getCity(),
                transaction.getState(),
                transaction.getZipcode());
    }

    @Override
    public void deleteTransactionById(int id) {
        final String DELETE_TRANSACTION = "DELETE FROM transactions WHERE id = ?";
        jdbc.update(DELETE_TRANSACTION, id);
    }

    @Override
    public List<Transaction> getTransactionsForUser(User user) {
        final String SELECT_TRANSACTION_FOR_USER = "SELECT * FROM transactions WHERE userid = ?";
        List<Transaction> transactions = jdbc.query(SELECT_TRANSACTION_FOR_USER,
                new TransactionMapper(), user.getId());
        associateUserAndCar(transactions);
        return transactions;
    }

    private User getUserForTransaction(int id) {
        final String SELECT_TEACHER_FOR_COURSE = "SELECT u.* FROM user u "
                + "JOIN transactions t ON u.id = t.userid WHERE t.id = ?";
        return jdbc.queryForObject(SELECT_TEACHER_FOR_COURSE, new UserMapper(), id);
    }

    private Car getCarForTransaction(int id) {
        final String SELECT_STUDENTS_FOR_COURSE = "SELECT c.* FROM car c "
                + "JOIN transactions t ON t.carid = c.id WHERE t.id = ?";
        return jdbc.query(SELECT_STUDENTS_FOR_COURSE, new CarMapper(), id);
    }

    private void associateUserAndCar(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transaction.setUser(getUserForTransaction(transaction.getId()));
            transaction.setCar(getCarForTransaction(transaction.getId()));
        }
    }

     public static final class TransactionMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet rs, int index) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setPurchasePrice(rs.getInt("purchasePrice"));
            transaction.setPurchaseType(rs.getString("purchaseType"));
            transaction.setEmail(rs.getString("email"));
            transaction.setPhone(rs.getString("phone"));
            transaction.setStreetMain(rs.getString("street1"));
            transaction.setStreetAlt(rs.getString("street2"));
            transaction.setCity(rs.getString("city"));
            transaction.setState(rs.getString("state"));
            transaction.setZipcode(rs.getString("zipcode"));
            return transaction;
        }
    }
    
}
