package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.SalesReportDao;
import com.mthree.cardealership.dao.TransactionDao;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.Role;
import com.mthree.cardealership.entities.SalesReport;
import com.mthree.cardealership.entities.Transaction;
import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ReportController {
    @Autowired
    TransactionDao transactionDao;

    @Autowired
    UserDao userDao;

    @Autowired
    SalesReportDao salesReportDao;

    @GetMapping("admin/reports/index")
    public String displayReports() {
        return "reports";
    }

    @GetMapping("admin/reports/sales")
    public String displaySalesReport(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "salesReport";
    }

    @GetMapping("admin/reports/inventory")
    public String displayInventoryReport(Model model) {
        return "inventoryReport";
    }

    @PostMapping("admin/reports/sales")
    public List<SalesReport> displaySalesReport(@Valid User user, BindingResult result, HttpServletRequest request, Model model) {

        Date fromDate = Date.valueOf(request.getParameter("fromDate"));
        Date toDate= Date.valueOf(request.getParameter("toDate"));
        String userId = request.getParameter("userid");
        User selectedUser = userDao.getUserById(Integer.parseInt(userId));

        return salesReportDao.getSalesReports(selectedUser, fromDate, toDate);
    }
}
