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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String displaySalesReport(HttpServletRequest request, Model model) {
        List<SalesReport> salesReports;
        String fromDateString = request.getParameter("fromDate");
        String toDateString = request.getParameter("toDate");
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("fromDate", fromDateString);
        dateMap.put("toDate", toDateString);
//        Date fromDate = Date.valueOf(request.getParameter("fromDate"));
//        Date toDate= Date.valueOf(request.getParameter("toDate"));

        String userId = request.getParameter("user");

        if(!userId.equals("all")){
            User selectedUser = userDao.getUserById(Integer.parseInt(userId));
            salesReports = salesReportDao.getSalesReportsForUser(selectedUser, dateMap);
        }else
            salesReports = salesReportDao.getAllSalesReports(dateMap);

        model.addAttribute("sales", salesReports);
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "salesReport";
    }
}
