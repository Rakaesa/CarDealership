package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.SalesReport;
import com.mthree.cardealership.entities.User;

import java.util.Date;
import java.util.List;

public interface SalesReportDao {
    List<SalesReport> getAllSalesReports(Date fromDate, Date toDate);
    List<SalesReport> getSalesReportsForUser(User user, Date fromDate, Date toDate);
}
