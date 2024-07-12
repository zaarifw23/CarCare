package Controllers;

import Models.ReportModel;
import ServiceLayer.ReportService;
import Views.ReportView;

import java.sql.SQLException;

public class ReportController {
    private ReportModel model;
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.model = new ReportModel(reportService);
        this.reportService = reportService;
    }

    public void connectToDatabase() {
        try {
            reportService.connectToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateMonthlyReport(String year, int month, ReportView view) {
        double totalSales = model.fetchTotalSales(year, month);
        double totalExpenses = model.fetchTotalExpenses(year, month);
        double profit = model.calculateProfit(totalSales, totalExpenses);

        StringBuilder report = new StringBuilder();
        report.append("Monthly Report for ").append(view.getMonthName(month)).append(" ").append(year).append(":\n\n");
        report.append("Total Sales: $").append(totalSales).append("\n");
        report.append("Total Expenses: $").append(totalExpenses).append("\n");
        report.append("Profit: $").append(profit).append("\n");

        String profitLabel = (profit >= 0) ? "Profit: $" + profit : "Loss: $" + Math.abs(profit);

        view.updateReportTextArea(report.toString(), profitLabel);

    }
}
