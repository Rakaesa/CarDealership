package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.SalesReport;
import com.mthree.cardealership.entities.User;

import java.util.Date;
import java.util.List;

public interface SalesReportDao {

    List<SalesReport> getSalesReports(User user, Date fromDate, Date toDate);
}
