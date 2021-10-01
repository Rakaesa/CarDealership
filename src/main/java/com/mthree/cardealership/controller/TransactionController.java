/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.TransactionDao;
import com.mthree.cardealership.entities.Contact;
import com.mthree.cardealership.entities.Transaction;
import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author conno
 */
@Controller
public class TransactionController {
    @Autowired
    TransactionDao transactionDao;

    @GetMapping("admin/transactions")
    public String displayTransactions(Model model) {
        List<Transaction> transactions = transactionDao.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    @GetMapping("admin/transactionsForUser")
    public String displayTransactionsForUser(User user, Model model) {
        List<Transaction> transactions = transactionDao.getTransactionsForUser(user);
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    @PostMapping("admin/addTransaction")
    public String addTransaction(Transaction transaction, HttpServletRequest request) {
        transactionDao.addTransaction(transaction);
        return "redirect:/admin/transactions";
    }

    @GetMapping("admin/deleteTransaction")
    public String deleteTransaction(Integer id) {
        transactionDao.deleteTransactionById(id);
        return "redirect:/admin/transactions";
    }
    
}
