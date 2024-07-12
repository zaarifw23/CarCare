package ServiceLayer;

import DatabaseLayer.DatabaseConnection_01;
import Models.Customersdetails;
import Controllers.customerController;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class customerDb {
    public int addToDatabase(Customersdetails customersdetails) throws SQLException {
            String insertQuery = "INSERT INTO customerorder (CustomerName, CustomerEmail, CustomercontactNo, OrderDescription, OrderPrice, OrderStatus, OrderDate) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = DatabaseConnection_01.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, customersdetails.getCustomerName());
            preparedStatement.setString(2, customersdetails.getCustomerEmail());
            preparedStatement.setString(3, customersdetails.getCustomercontactNo());
            preparedStatement.setString(4, customersdetails.getOrderDescription());
            preparedStatement.setString(5, customersdetails.getOrderStatus());
            preparedStatement.setString(6, customersdetails.getOrderPrice());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedID = generatedKeys.getInt(1);
                        customersdetails.setCustomerID(generatedID);

                        customerController customercontroller = customersdetails.getCustomercontroller();

                        if (customercontroller != null) {
                            customercontroller.addCustomer(customersdetails);
                        }
                        return generatedID;
                    } else {
                        throw new SQLException("Failed to get generated keys after adding Supplier");
                    }
                }
            }else {
                throw new SQLException("Failed to add Customer");
            }

        }
    }
    public void deleteFromDatabase(Customersdetails customersdetails) throws SQLException {
        if (customersdetails.getCustomerID() != 0) {
            String deleteQuery = "DELETE FROM customerorder WHERE CustomerID = ?";

            try (Connection connection = DatabaseConnection_01.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

                preparedStatement.setInt(1, customersdetails.getCustomerID());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected < 0) {
                    throw new SQLException("Failed to delete Customer");
                }
            }
        } else {
            throw new SQLException("order ID is not set");
        }
    }
    public void updateDatabase(Customersdetails customersdetails) throws SQLException {
        String updateQuery = "UPDATE customerorder SET CustomerName=?, CustomerEmail=?, CustomercontactNo=?, OrderDescription=?,OrderStatus=?,OrderPrice=? WHERE CustomerID=?";

        try (Connection connection = DatabaseConnection_01.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, customersdetails.getCustomerName());
            preparedStatement.setString(2, customersdetails.getCustomerEmail());
            preparedStatement.setString(3, customersdetails.getCustomercontactNo());
            preparedStatement.setString(4, customersdetails.getOrderDescription());
            preparedStatement.setString(5, customersdetails.getOrderStatus());
            preparedStatement.setString(6, customersdetails.getOrderPrice());
            preparedStatement.setInt(7, customersdetails.getCustomerID());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new SQLException("Failed to update Customer Order");
            }else {
                System.out.println("customer updated successfully in the database");
            }
        }
    }
    public DefaultTableModel getCustomerTableModel() {
        String selectQuery = "SELECT * FROM customerorder";
        DefaultTableModel model = new DefaultTableModel();

        try (Connection connection = DatabaseConnection_01.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(selectQuery)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            String[] col = new String[cols];
            for (int i = 0; i < cols; i++) {
                col[i] = rsmd.getColumnName(i + 1);
            }

            model.setColumnIdentifiers(col);

            while (rs.next()) {
                String CustomerID = rs.getString(1);
                String CustomerName = rs.getString(2);
                String CustomerEmail = rs.getString(3);
                String CustomercontactNo = rs.getString(4);
                String OrderDescription = rs.getString(5);
                String OrderStatus = rs.getString(6);
                String OrderPrice = rs.getString(7);
                String OrderDate = rs.getString(8);


                String[] row = {CustomerID, CustomerName, CustomerEmail, CustomercontactNo, OrderDescription, OrderStatus, OrderPrice, OrderDate};
                model.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer Order data: " + e.getMessage());
        }

        return model;
    }


}
