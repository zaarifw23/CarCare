package Models;

import Controllers.customerController;
import ServiceLayer.customerDb;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Customersdetails {

    public customerDb customerdb;
    private static int CustomerID;

    private String CustomerName;

    private String OrderDescription;

    private String OrderStatus;
    private String OrderPrice;

    private String CustomerEmail;
    private String CustomercontactNo;
    private customerController customercontroller;

    public Customersdetails(String CustomerName, String CustomerEmail, String CustomercontactNo, String OrderDescription, String OrderStatus, String OrderPrice) {
        this.CustomerName = CustomerName;
        this.CustomerEmail = CustomerEmail;
        this.CustomercontactNo = CustomercontactNo;
        this.OrderDescription = OrderDescription;

        this.OrderStatus = OrderStatus;
        this.OrderPrice = OrderPrice;
        this.customerdb = new customerDb();

    }



    public Customersdetails() {

    }

    public customerController getCustomercontroller() {
        return customercontroller;
    }


    public String getOrderDescription() {
        return OrderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        OrderDescription = orderDescription;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        OrderPrice = orderPrice;
    }

    public static int getCustomerID() {
        return CustomerID;
    }

    public static void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomercontactNo() {
        return CustomercontactNo;
    }

    public void setCustomercontactNo(String customercontactNo) {
        CustomercontactNo = customercontactNo;
    }

    public int addDatabase() throws SQLException {
        return  customerdb.addToDatabase(this);
    }

    public void deleteDatabase() throws SQLException {
        if (this.customerdb == null) {
            this.customerdb = new customerDb();
        }
        customerdb.deleteFromDatabase(this);
    }

    public void updateFromDatabase() throws SQLException {
        if (this.customerdb == null) {
            this.customerdb = new customerDb();
        }
        customerdb.updateDatabase(this);
    }


    public DefaultTableModel getCustomerTableModel() {
        if (this.customerdb == null) {
            this.customerdb = new customerDb();
        }
        return  customerdb.getCustomerTableModel();
    }}
