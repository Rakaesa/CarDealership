package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.SalesReport;
import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class SalesReportDaoDB implements SalesReportDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<SalesReport> getSalesReportsForUser(User user, Map<String, String> dateMap) {
        List<SalesReport> salesReports;
        final String SELECT_SALESREPORT_FOR_USER;
        if(dateMap.get("fromDate").isEmpty() && dateMap.get("toDate").isEmpty()){
            SELECT_SALESREPORT_FOR_USER = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id WHERE u.id = ? group by user";
            salesReports = jdbc.query(SELECT_SALESREPORT_FOR_USER, new SalesReportMapper(), user.getId());
        }
        else if(!dateMap.get("fromDate").isEmpty() && dateMap.get("toDate").isEmpty()){
            SELECT_SALESREPORT_FOR_USER = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id WHERE u.id = ? and t.purchasedate >= ? group by user";
            salesReports = jdbc.query(SELECT_SALESREPORT_FOR_USER,
                    new SalesReportMapper(), user.getId(), Date.valueOf(dateMap.get("fromDate")));
        }else if(dateMap.get("fromDate").isEmpty() && !dateMap.get("toDate").isEmpty()){
            SELECT_SALESREPORT_FOR_USER = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id WHERE u.id = ? and t.purchasedate <= ? group by user";
            salesReports = jdbc.query(SELECT_SALESREPORT_FOR_USER,
                    new SalesReportMapper(), user.getId(), Date.valueOf(dateMap.get("toDate")));
        }else{
            SELECT_SALESREPORT_FOR_USER = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id WHERE u.id = ? and t.purchasedate between ? and ? group by user";
            salesReports = jdbc.query(SELECT_SALESREPORT_FOR_USER, new SalesReportMapper(), user.getId(),
                    Date.valueOf(dateMap.get("fromDate")), Date.valueOf(dateMap.get("toDate")));
        }

        return salesReports;
    }

    @Override
    public List<SalesReport> getAllSalesReports(Map<String, String> dateMap) {
        List<SalesReport> salesReports;
        final String SELECT_ALLSALESREPORTS;
        if(dateMap.get("fromDate").isEmpty() && dateMap.get("toDate").isEmpty()){
            SELECT_ALLSALESREPORTS = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id group by user";
            salesReports = jdbc.query(SELECT_ALLSALESREPORTS, new SalesReportMapper());
        }
        else if(!dateMap.get("fromDate").isEmpty() && dateMap.get("toDate").isEmpty()){
            SELECT_ALLSALESREPORTS = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id WHERE t.purchasedate >= ? group by user";
            salesReports = jdbc.query(SELECT_ALLSALESREPORTS, new SalesReportMapper(), Date.valueOf(dateMap.get("fromDate")));
        }else if(dateMap.get("fromDate").isEmpty() && !dateMap.get("toDate").isEmpty()){
            SELECT_ALLSALESREPORTS = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id WHERE t.purchasedate <= ? group by user";
            salesReports = jdbc.query(SELECT_ALLSALESREPORTS, new SalesReportMapper(), Date.valueOf(dateMap.get("toDate")));
        }else{
            SELECT_ALLSALESREPORTS = "SELECT concat(u.firstname,' ', u.lastname) as user," +
                    "sum(t.purchasePrice) as TotalSales, count(t.carid) as TotalVehicles, t.purchasedate FROM " +
                    "transactions t join users u on t.userid=u.id WHERE t.purchasedate between ? and ? group by user";
            salesReports = jdbc.query(SELECT_ALLSALESREPORTS, new SalesReportMapper(),
                    Date.valueOf(dateMap.get("fromDate")), Date.valueOf(dateMap.get("toDate")));
        }

        return salesReports;
    }

    public static final class SalesReportMapper implements RowMapper<SalesReport> {

        @Override
        public SalesReport mapRow(ResultSet rs, int index) throws SQLException {
            SalesReport salesReport = new SalesReport();
            //salesReport.setId(rs.getInt("id"));
            salesReport.setUser(rs.getString("user"));
            salesReport.setTotalSales(rs.getDouble("totalsales"));
            salesReport.setTotalVehicles(rs.getInt("totalvehicles"));
            //salesReport.setPurchaseDate(rs.getDate("purchasedate"));
            return salesReport;
        }
    }
}
