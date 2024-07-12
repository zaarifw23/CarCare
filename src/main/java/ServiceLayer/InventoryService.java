package ServiceLayer;

import Controllers.InventoryController;
import DatabaseLayer.DatabaseConnection_01;
import Models.Inventory;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class InventoryService {

    public int addItemToDatabase(Inventory Item) throws SQLException {
        String insertQuery = "INSERT INTO Items (ItemName, ItemCategory, ItemPrice, ItemQuantity, ItemDate) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = DatabaseConnection_01.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, Item.getItemName());
            preparedStatement.setString(2, Item.getItemCategory());
            preparedStatement.setDouble(3, Item.getItemPrice());
            preparedStatement.setInt(4, Item.getItemQuantity());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedID = generatedKeys.getInt(1);
                        Item.setItemID(generatedID);

                        InventoryController inventoryController = Item.getInventoryController();
                        if (inventoryController != null) {
                            inventoryController.addItem(Item);
                        }
                        return generatedID;
                    } else {
                        throw new SQLException("Failed to get generated keys after adding Supplier");
                    }
                }
            } else {
                throw new SQLException("Failed to add Supplier");
            }
        }
    }

    public void deleteItemFromDatabase(Inventory Item) throws SQLException {
        if (Item.getItemID() != 0) {
            String deleteQuery = "DELETE FROM Items WHERE ItemID = ?";

            try (Connection connection = DatabaseConnection_01.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

                preparedStatement.setInt(1, Item.getItemID());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected < 0) {
                    throw new SQLException("Failed to delete Item");
                }
            }
        } else {
            throw new SQLException("Item ID is not set");
        }
    }

    public void updateDatabase(Inventory Item) throws SQLException {
        String updateQuery = "UPDATE Items SET ItemName=?, ItemCategory=?, ItemPrice=?, ItemQuantity=? WHERE ItemID=?";

        try (Connection connection = DatabaseConnection_01.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, Item.getItemName());
            preparedStatement.setString(2, Item.getItemCategory());
            preparedStatement.setDouble(3, Item.getItemPrice());
            preparedStatement.setInt(4, Item.getItemQuantity());
            preparedStatement.setInt(5, Item.getItemID());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new SQLException("Failed to update Item");
            } else {
                System.out.println("Item updated successfully in the database");
            }
        }
    }

    public DefaultTableModel getInventoryTableModel() {
        String selectQuery = "SELECT * FROM Items";
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
                String ItemID = rs.getString(1);
                String ItemName = rs.getString(2);
                String ItemCategory = rs.getString(3);
                String ItemPrice = rs.getString(4);
                String ItemItemQuantity = rs.getString(5);

                String[] row = {ItemID, ItemName, ItemCategory, ItemPrice, ItemItemQuantity};
                model.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving item data: " + e.getMessage());
            return null; // Return null in case of an exception
        }

        return model;
    }

}
