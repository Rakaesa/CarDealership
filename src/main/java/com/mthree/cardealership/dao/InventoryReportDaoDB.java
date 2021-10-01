package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.InventoryReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryReportDaoDB implements InventoryReportDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<InventoryReport> getNewInventoryReports() {
        List<InventoryReport> inventoryReports;
        final String SELECT_NEWINVENTORYREPORTS;
        SELECT_NEWINVENTORYREPORTS = "SELECT c.year,ma.make,mo.model,count(c.id) as count,sum(c.mrsp) as StockValue " +
                "FROM car c join model mo on c.modelid=mo.id join make ma on mo.makeid=ma.id " +
                "WHERE c.type='new' and c.purchased=false group by c.year, ma.make, mo.model";
        inventoryReports = jdbc.query(SELECT_NEWINVENTORYREPORTS,
                new InventoryReportMapper());
        return inventoryReports;
    }

    @Override
    public List<InventoryReport> getUsedInventoryReports() {
        List<InventoryReport> inventoryReports;
        final String SELECT_USEDINVENTORYREPORTS;

        SELECT_USEDINVENTORYREPORTS = "SELECT c.year,ma.make,mo.model,count(c.id) as count,sum(c.mrsp) as StockValue \n" +
                "FROM car c join model mo on c.modelid=mo.id join make ma on mo.makeid=ma.id \n" +
                "WHERE c.type='used' and c.purchased=false group by c.year, ma.make, mo.model";
        inventoryReports = jdbc.query(SELECT_USEDINVENTORYREPORTS,
                new InventoryReportMapper());
        return inventoryReports;
    }

    public static final class InventoryReportMapper implements RowMapper<InventoryReport> {

        @Override
        public InventoryReport mapRow(ResultSet rs, int index) throws SQLException {
            InventoryReport inventoryReport = new InventoryReport();
            inventoryReport.setYear(rs.getInt("year"));
            inventoryReport.setMake(rs.getString("make"));
            inventoryReport.setModel(rs.getString("model"));
            inventoryReport.setCount(rs.getInt("count"));
            inventoryReport.setStockValue(rs.getDouble("stockvalue"));

            return inventoryReport;
        }
    }
}
