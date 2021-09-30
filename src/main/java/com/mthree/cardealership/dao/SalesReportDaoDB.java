package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.SalesReport;
import com.mthree.cardealership.entities.Transaction;
import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SalesReportDaoDB implements SalesReportDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<SalesReport> getSalesReportsForUser(User user, Date fromDate, Date toDate) {
        List<SalesReport> salesReports;
        final String SELECT_SALESREPORT_FOR_USER;
        if(fromDate == null && toDate == null){
            SELECT_SALESREPORT_FOR_USER = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on " +
                    "t.userid=u.id WHERE u.id = ?";
        }
        else if(fromDate != null && toDate == null){
            SELECT_SALESREPORT_FOR_USER = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on " +
                    "t.userid=u.id WHERE u.id = ? and t.purchasedate >= fromDate";
        }else if(fromDate == null && toDate != null){
            SELECT_SALESREPORT_FOR_USER = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on " +
                    "t.userid=u.id WHERE u.id = ? and t.purchasedate <= toDate";
        }else{
            SELECT_SALESREPORT_FOR_USER = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on " +
                    "t.userid=u.id WHERE u.id = ? and t.purchasedate between fromDate and toDate";
        }

        salesReports = jdbc.query(SELECT_SALESREPORT_FOR_USER,
                new SalesReportMapper(), user.getId());

        return salesReports;
    }

    @Override
    public List<SalesReport> getAllSalesReports(Date fromDate, Date toDate) {
        List<SalesReport> salesReports;
        final String SELECT_ALLSALESREPORTS;
        if(fromDate == null && toDate == null){
            SELECT_ALLSALESREPORTS = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id";
        }
        else if(fromDate != null && toDate == null){
            SELECT_ALLSALESREPORTS = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on " +
                    "t.userid=u.id WHERE t.purchasedate >= fromDate";
        }else if(fromDate == null && toDate != null){
            SELECT_ALLSALESREPORTS = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on " +
                    "t.userid=u.id WHERE t.purchasedate <= toDate";
        }else{
            SELECT_ALLSALESREPORTS = "SELECT u.firstname+' '+u.lastname as User," +
                    "sum(t.purchasePrice) as Total Price, count(t.carid) as Total Vehicles, t.purchasedate FROM " +
                    "transactions t join users u on " +
                    "t.userid=u.id WHERE t.purchasedate between fromDate and toDate";
        }

        salesReports = jdbc.query(SELECT_ALLSALESREPORTS, new SalesReportMapper());

        return salesReports;
    }

    public static final class SalesReportMapper implements RowMapper<SalesReport> {

        @Override
        public SalesReport mapRow(ResultSet rs, int index) throws SQLException {
            SalesReport salesReport = new SalesReport();
            salesReport.setId(rs.getInt("id"));
            salesReport.setName(rs.getString("name"));
            salesReport.setTotalSales(rs.getDouble("totalsales"));
            salesReport.setTotalVehicles(rs.getDouble("totalvehicles"));
            salesReport.setPurchaseDate(rs.getDate("purchasedate"));
            return salesReport;
        }
    }
}
