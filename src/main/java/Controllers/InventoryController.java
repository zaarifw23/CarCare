package Controllers;

import Models.Inventory;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
public class InventoryController {
    private ArrayList<Inventory> Items;
    public InventoryController(ArrayList<Inventory> Items){
        this.Items = Items;
    }
    public void addItem(Inventory newItem){
        Items.add(newItem);
    }
    public void updateItem(int ItemID, String ItemName, String ItemCategory, Double ItemPrice, int ItemQuantity) {

        Inventory Item = new Inventory();
        if (ItemID > 0 ) {
            Item.setItemID(ItemID);
            Item.setItemName(ItemName);
            Item.setItemCategory(ItemCategory);
            Item.setItemPrice(ItemPrice);
            Item.setItemQuantity(ItemQuantity);

            try {
                Item.updateDatabase();
                System.out.println("Item updated successfully in the controller");
            } catch (SQLException e) {
                System.out.println("Error updating item in the controller: " + e.getMessage());
            }
        } else {
            System.out.println("Error: Item not found with ID " + ItemID);
        }
    }

    public void removeItem(int ItemID) {
        Inventory itemToRemove = findItemByID(ItemID);
        if (itemToRemove != null) {
            Items.remove(itemToRemove);
        } else {
            System.out.println("Error: Item not found with ID " + ItemID);
        }
    }

    public Inventory findItemByID(int ItemID) {
        System.out.println("Searching for item with ID: " + ItemID);

        for (Inventory Item : Items) {
            System.out.println("Checking Item with ID: " + Item.getItemID());
            if (Item.getItemID() == ItemID) {
                System.out.println("Item Found!");
                return Item;
            }
        }
        System.out.println("Item not found with ID: " + ItemID);
        return null;
    }
    public DefaultTableModel getInventoryTableModel() {
        Inventory inventoryModel = new Inventory();
        return inventoryModel.getInventoryTableModel();
    }

}
