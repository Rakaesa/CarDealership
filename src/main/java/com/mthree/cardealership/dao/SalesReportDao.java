package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.SalesReport;
import com.mthree.cardealership.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SalesReportDao {
    List<SalesReport> getAllSalesReports(Map<String, String> dateMap);
    List<SalesReport> getSalesReportsForUser(User user, Map<String, String> dateMap);
}
