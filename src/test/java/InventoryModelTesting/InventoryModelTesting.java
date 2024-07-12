package InventoryModelTesting;
import Models.Inventory;
import org.junit.jupiter.api.*;
public class InventoryModelTesting {
    static Inventory item;

    @BeforeAll
    public static void creaateInventory(){
        item = new Inventory();
    }
    @BeforeEach
    public void setInventory(){
        item.setItemName("Coolant");
        item.setItemCategory("Engine");
        item.setItemPrice(299.9);
        item.setItemQuantity(23);
    }
    @Test
    public void testInventory(){
        Assertions.assertEquals("Coolant", item.getItemName());
        Assertions.assertEquals("Engine", item.getItemCategory());
        Assertions.assertEquals(299.9, item.getItemPrice());
        Assertions.assertEquals(23, item.getItemQuantity());
    }
    @AfterEach
    public void cleanData(){
        item.setItemName(null);
        item.setItemCategory(null);
        item.setItemPrice(0.0);
        item.setItemQuantity(0);
    }
    @AfterAll
    public static void deleteInventory(){
        item = null;
    }
}