package Models;

import ServiceLayer.ReportService;

import java.sql.SQLException;

public class ReportModel {
    private ReportService reportService;

    public ReportModel(ReportService reportService) {
        this.reportService = reportService;
    }

    public double fetchTotalSales(String year, int month) {
        try {
            return reportService.fetchTotalSales(year, month);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double fetchTotalExpenses(String year, int month) {
        try {
            return reportService.fetchTotalExpenses(year, month);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double calculateProfit(double totalSales, double totalExpenses) {
        return totalSales - totalExpenses;
    }
    public ReportModel(){}
}
