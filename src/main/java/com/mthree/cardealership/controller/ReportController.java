package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.InventoryReportDao;
import com.mthree.cardealership.dao.SalesReportDao;
import com.mthree.cardealership.dao.TransactionDao;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.InventoryReport;
import com.mthree.cardealership.entities.SalesReport;
import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    TransactionDao transactionDao;

    @Autowired
    UserDao userDao;

    @Autowired
    SalesReportDao salesReportDao;

    @Autowired
    InventoryReportDao inventoryReportDao;

    @GetMapping("admin/reports/index")
    public String displayReports() {
        return "reports";
    }

    @GetMapping("admin/reports/sale")
    public String displaySalesReport(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "salesReport";
    }

    @GetMapping("admin/reports/inventory")
    public String displayInventoryReport(Model model) {
        List<InventoryReport> newInventoryReports = inventoryReportDao.getNewInventoryReports();
        List<InventoryReport> usedInventoryReports = inventoryReportDao.getUsedInventoryReports();
        model.addAttribute("newinventories", newInventoryReports);
        model.addAttribute("usedinventories", usedInventoryReports);
        return "inventoryReport";
    }

    @PostMapping("admin/reports/sales")
    public String displaySalesReport(@Valid User user, BindingResult result, HttpServletRequest request, Model model) {
        List<SalesReport> salesReports;
        Date fromDate = Date.valueOf(request.getParameter("fromDate"));
        Date toDate= Date.valueOf(request.getParameter("toDate"));

        String allUsers = request.getParameter("all");

        if(allUsers.equals("all")){
            salesReports = salesReportDao.getAllSalesReports(fromDate, toDate);
        }else{
            String userId = request.getParameter("userid");
            User selectedUser = userDao.getUserById(Integer.parseInt(userId));
            salesReports = salesReportDao.getSalesReportsForUser(selectedUser, fromDate, toDate);
        }

        model.addAttribute("sales", salesReports);
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "salesReport";
    }
}
