package CustomerOrderModelTesting;

import Models.Customersdetails;
import org.junit.jupiter.api.*;
public class CustomersdetailsModelTesting {

    static Customersdetails order;

    @BeforeAll
    public static void createCustomerOrder(){
        order = new Customersdetails();
    }

    @BeforeEach
    public void setSupplier(){
        order.setCustomerName("Adonis");
        order.setOrderDescription("Brakes");
        order.setOrderStatus("Shipped");
        order.setOrderPrice("12500");
        order.setCustomerEmail("Adonis@gmail.com");
        order.setCustomercontactNo("077");
    }
    @Test
    public void testSupplier(){
        Assertions.assertEquals("Adonis", order.getCustomerName());
        Assertions.assertEquals("Brakes", order.getOrderDescription());
        Assertions.assertEquals("Shipped", order.getOrderStatus());
        Assertions.assertEquals("12500", order.getOrderPrice());
        Assertions.assertEquals("Adonis@gmail.com", order.getCustomerEmail());
        Assertions.assertEquals("077", order.getCustomercontactNo());
    }
    @AfterEach
    public void cleanData(){
        order.setCustomerName(null);
        order.setOrderDescription(null);
        order.setOrderStatus(null);
        order.setOrderPrice(null);
        order.setCustomerEmail(null);
        order.setCustomercontactNo(null);
    }
    @AfterAll
    public static void deleteCustomerOder(){
        order = null;
    }
}
