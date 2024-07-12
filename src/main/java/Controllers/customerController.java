package Controllers;

import Models.Customersdetails;
import ServiceLayer.customerDb;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
public class customerController {
    private ArrayList<Customersdetails> customer;
    private customerDb customerdb;

    public customerController(){
        customerdb = new customerDb();
    }

    public customerController(ArrayList<Customersdetails> customer)
    {
        this.customer=customer;
    }

    public void addCustomer(Customersdetails newcustomers)
    {
        customer.add(newcustomers);
    }

    public void updateCustomer(int CustomerID, String CustomerName, String CustomerEmail, String CustomercontactNo, String OrderDescription, String OrderStatus, String OrderPrice)
    {
        Customersdetails customers= new Customersdetails();
        if(CustomerID>0)
        {
            customers.setCustomerID(CustomerID);
            customers.setCustomerName(CustomerName);
            customers.setCustomerEmail(CustomerEmail);
            customers.setCustomercontactNo(CustomercontactNo);
            customers.setOrderDescription(OrderDescription);
            customers.setOrderStatus(OrderStatus);
            customers.setOrderPrice(OrderPrice);



            try {
                customers.updateFromDatabase();
                System.out.println(" Customer Order updated successfully in the controller");
            }catch (SQLException e){
                System.out.println("Error updating Customer in the controller: " + e.getMessage());
            }

        }else {
            System.out.println("Error: customer order not found with ID " + CustomerID);

        }
    }
    public void removeCustomerOrder(int CustomerID) {
        Customersdetails customersdetailsToRemove = findCustomerOrderByID(CustomerID);
        if (customersdetailsToRemove != null) {
            customer.remove(customersdetailsToRemove);
        } else {
            System.out.println("Error: customer order not found with ID " + CustomerID);

        }
    }

    public Customersdetails findCustomerOrderByID(int CustomerID) {
        System.out.println("Searching for customer order with ID: " + CustomerID);

        for (Customersdetails customers : customer) {
            System.out.println("Checking customer order with ID: " + customers.getCustomerID());
            if (customers.getCustomerID() == CustomerID) {
                System.out.println(" customer order found!");
                return customers;
            }
        }
        System.out.println("customer order not found with ID: " + CustomerID);
        return null;
    }




    public DefaultTableModel getCustomerTableModel() {
        Customersdetails customersdetailsModel = new Customersdetails();
        return customersdetailsModel.getCustomerTableModel();
    }
}
