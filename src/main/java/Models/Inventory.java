package Models;

import Controllers.InventoryController;
import ServiceLayer.InventoryService;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class Inventory {
    private int ItemID;
    private String ItemName;
    private String ItemCategory;
    private double ItemPrice;
    private int ItemQuantity;
    private InventoryController InventoryController;
    private final InventoryService InventoryService;

    public Inventory(String ItemName, String ItemCategory, double ItemPrice, int ItemQuantity) {
        this.ItemName = ItemName;
        this.ItemCategory = ItemCategory;
        this.ItemPrice = ItemPrice;
        this.ItemQuantity = ItemQuantity;
        this.InventoryService = new InventoryService();
    }

    public Controllers.InventoryController getInventoryController() {
        return InventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.InventoryController = inventoryController;
    }

    public Inventory(){
        this.InventoryService = new InventoryService();
    }

    public int getItemID() {
        return ItemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getItemCategory() {
        return ItemCategory;
    }

    public double getItemPrice() {
        return ItemPrice;
    }

    public int getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setItemCategory(String itemCategory) {
        ItemCategory = itemCategory;
    }

    public void setItemPrice(double itemPrice) {
        ItemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public int addToDatabase() throws SQLException {
        return InventoryService.addItemToDatabase(this);
    }

    public void deleteFromDatabase() throws SQLException {
        InventoryService.deleteItemFromDatabase(this);
    }

    public void updateDatabase() throws SQLException {
        InventoryService.updateDatabase(this);
    }

    public DefaultTableModel getInventoryTableModel() {
        return InventoryService.getInventoryTableModel();
    }
}
