package InventoryControllerTesting;

import Controllers.InventoryController;
import Models.Inventory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class inventoryControllerTesting {
    static InventoryController inventoryController;
    static ArrayList<Inventory> items;
    static Inventory item;

    @BeforeAll
    public static void createInventoryController() {
        items = new ArrayList<>();
        inventoryController = new InventoryController(items);
    }

    @BeforeEach
    public void setInventoryController() {
        item = new Inventory();
        item.setItemName("Coolant");
        item.setItemCategory("Engine");
        item.setItemPrice(299.9);
        item.setItemQuantity(23);
    }

    @Test
    public void testInventoryController() {
        Inventory item1 = new Inventory(item.getItemName(), item.getItemCategory(), item.getItemPrice(), item.getItemQuantity());
        items.add(item1);

        Assertions.assertTrue(items.contains(item1));
        Assertions.assertEquals(1, items.size());
    }

    @AfterEach
    public void cleanData() {
        item.setItemName(null);
        item.setItemCategory(null);
        item.setItemPrice(0.0);
        item.setItemQuantity(0);
    }

    @AfterAll
    public static void deleteInventoryController() {
        items = null;
        item = null;
        inventoryController = null;
    }
}