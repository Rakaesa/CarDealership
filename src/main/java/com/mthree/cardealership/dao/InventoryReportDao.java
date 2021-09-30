package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.InventoryReport;

import java.util.List;

public interface InventoryReportDao {
    List<InventoryReport> getNewInventoryReports();
    List<InventoryReport> getUsedInventoryReports();
}
