package ReportModelTesting;
import Models.ReportModel;
import org.junit.jupiter.api.*;
public class ReportModelTesting {
    static ReportModel reportModel;
    static double totalSales;
    static double totalExpenses;
    static double result;

    @BeforeAll
    public static void createReportModel() {
        reportModel = new ReportModel();
    }

    @BeforeEach
    public void setUpReportModel() {
        totalSales = 1000.0;
        totalExpenses = 500.0;
    }

    @Test
    public void testReportModel() {
        result = reportModel.calculateProfit(totalSales, totalExpenses);
        Assertions.assertEquals(500.0, result);
    }

    @AfterEach
    public void clearReportModel() {
        totalSales = 0.0;
        totalExpenses = 0.0;
        result = 0.0;
    }

    @AfterAll
    public static void deleteReportModel() {
        reportModel = null;
    }
}
