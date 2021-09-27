/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Transaction;
import com.mthree.cardealership.entities.User;
import java.util.List;

/**
 *
 * @author conno
 */
public interface TransactionDao {
    
    Transaction getTransactionById(int id);
    List<Transaction> getAllTransactions();
    Transaction addTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    void deleteTransactionById(int id);
    
    List<Transaction> getTransactionsForUser(User user);
    
}
