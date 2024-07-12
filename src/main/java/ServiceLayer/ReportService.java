package ServiceLayer;

import DatabaseLayer.DatabaseConnection_01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportService {
    private DatabaseConnection_01 databaseConnector;

    public ReportService(DatabaseConnection_01 databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public void connectToDatabase() throws SQLException {
        databaseConnector.connect();
    }

    private Connection getConnection() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        if (connection == null || connection.isClosed()) {
            connectToDatabase();
            connection = databaseConnector.getConnection();
        }
        return connection;
    }

    public double fetchTotalSales(String year, int month) throws SQLException {
        String sql = "SELECT SUM(OrderPrice) FROM customerorder WHERE YEAR(OrderDate) = ? AND MONTH(OrderDate) = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, year);
            preparedStatement.setInt(2, month);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }
        throw new SQLException("Unable to fetch total sales");
    }

    public double fetchTotalExpenses(String year, int month) throws SQLException {
        String sql = "SELECT SUM(ItemPrice) FROM Items WHERE YEAR(ItemDate) = ? AND MONTH(ItemDate) = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, year);
            preparedStatement.setInt(2, month);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }
        throw new SQLException("Unable to fetch total expenses");
    }
}
