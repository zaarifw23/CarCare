package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import Controllers.ReportController;
import Controllers.customerController;
import DatabaseLayer.DatabaseConnection_01;
import Models.Customersdetails;
import ServiceLayer.ReportService;


public class customerOrderForm extends JFrame {
    private JTextField txtcustomerid;
    private JTextField txtCustomerName;
    private JTextField txtCustomerEmail;
    private JTextField txtCustomercontactNo;

    private JLabel lblCustomerName;
    private JLabel lblCustomerEmail;

    private JButton updateBtn;
    private JButton DeleteBtn;
    JPanel backPanel;
    private JButton AddBtn;
    private JButton displayBtn;
    private JButton OrderBtn;
    private JButton Suppliersbtn;
    private JButton InventoryBtn;
    private JButton EmployeesBtn;
    private JButton AssignJobsBtn;
    private JButton ReportsBtn;
    private JLabel lblcustomerId;
    private JTextField txtOrderDescription;
    private JTextField txtOrderPrice;

    private JTextField TxtCustomercontactNo;
    private JTextField txtOrderStatus;
    private JLabel lblOrderDescription;
    private JLabel lblOrderPrice;
    private JLabel lblOrderStatus;

    private JLabel lblCustomercontactNo;







    private customerController customercontroller;
    private final ArrayList<Customersdetails> customerlist;

    int customercount;

    private Customersdetails selectedCustomersdetails;

    public customerOrderForm(){


        customerlist = new ArrayList<>();

        customercontroller = new customerController(customerlist);

        txtcustomerid.setPreferredSize(new Dimension(200, 40));
        txtcustomerid.setFont(new Font("Arial", Font.PLAIN, 15));
        txtcustomerid.setForeground(Color.RED);

        txtCustomerName.setPreferredSize(new Dimension(200, 40));
        txtCustomerName.setFont(new Font("Arial", Font.PLAIN, 15));
        txtCustomerName.setForeground(Color.RED);

        txtCustomerEmail.setPreferredSize(new Dimension(200, 40));
        txtCustomerEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        txtCustomerEmail.setForeground(Color.RED);

        txtCustomercontactNo.setPreferredSize(new Dimension(200, 40));
        txtCustomercontactNo.setFont(new Font("Arial", Font.PLAIN, 15));
        txtCustomercontactNo.setForeground(Color.RED);

        txtOrderDescription.setPreferredSize(new Dimension(200, 40));
        txtOrderDescription.setFont(new Font("Arial", Font.PLAIN, 15));
        txtOrderDescription.setForeground(Color.RED);

        txtCustomercontactNo.setPreferredSize(new Dimension(200, 40));
        txtCustomercontactNo.setFont(new Font("Arial", Font.PLAIN, 15));
        txtCustomercontactNo.setForeground(Color.RED);


        txtOrderPrice.setPreferredSize(new Dimension(200, 40));
        txtOrderPrice.setFont(new Font("Arial", Font.PLAIN, 15));
        txtOrderPrice.setForeground(Color.RED);

        txtOrderStatus.setPreferredSize(new Dimension(200, 40));
        txtOrderStatus.setFont(new Font("Arial", Font.PLAIN, 15));
        txtOrderStatus.setForeground(Color.RED);




        lblCustomerName.setPreferredSize(new Dimension(200, 40));
        lblCustomerName.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCustomerName.setForeground(Color.black);
        lblCustomerName.setFont(new Font("Arial", Font.BOLD, 16));

        lblCustomerEmail.setPreferredSize(new Dimension(200, 40));
        lblCustomerEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCustomerEmail.setForeground(Color.black);
        lblCustomerEmail.setFont(new Font("Arial", Font.BOLD, 16));

        lblOrderDescription.setPreferredSize(new Dimension(200, 40));
        lblOrderDescription.setFont(new Font("Arial", Font.PLAIN, 15));
        lblOrderDescription.setForeground(Color.black);
        lblOrderDescription.setFont(new Font("Arial", Font.BOLD, 16));

        lblOrderPrice.setPreferredSize(new Dimension(200, 40));
        lblOrderPrice.setFont(new Font("Arial", Font.PLAIN, 15));
        lblOrderPrice.setForeground(Color.black);
        lblOrderPrice.setFont(new Font("Arial", Font.BOLD, 16));

        lblOrderStatus.setPreferredSize(new Dimension(200, 40));
        lblOrderStatus.setFont(new Font("Arial", Font.PLAIN, 15));
        lblOrderStatus.setForeground(Color.black);
        lblOrderStatus.setFont(new Font("Arial", Font.BOLD, 16));

        lblcustomerId.setPreferredSize(new Dimension(200, 40));
        lblcustomerId.setFont(new Font("Arial", Font.PLAIN, 15));
        lblcustomerId.setForeground(Color.black);
        lblcustomerId.setFont(new Font("Arial", Font.BOLD, 16));


        lblCustomercontactNo.setPreferredSize(new Dimension(200, 40));
        lblCustomercontactNo.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCustomercontactNo.setForeground(Color.black);
        lblCustomercontactNo.setFont(new Font("Arial", Font.BOLD, 16));

        AddBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String CustomerName = txtCustomerName.getText();
                String CustomerEmail = txtCustomerEmail.getText();
                String CustomercontactNo = txtCustomercontactNo.getText();
                String OrderDescription = txtOrderDescription.getText();
                String OrderPrice = txtOrderPrice.getText();
                String OrderStatus = txtOrderStatus.getText();


                Customersdetails newCustomersdetails = new Customersdetails(CustomerName, CustomerEmail, CustomercontactNo, OrderDescription, OrderPrice, OrderStatus);

                String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(CustomerEmail);

                if (matcher.matches()) {
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String contactNoRegex = "^[0-9]{10}$";

                Pattern numpattern = Pattern.compile(contactNoRegex);
                Matcher nummatcher = numpattern.matcher(CustomercontactNo);

                if (nummatcher.matches()) {
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid contact number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (CustomerName.isEmpty() || CustomerEmail.isEmpty() || CustomercontactNo.isEmpty() || OrderDescription.isEmpty() || OrderPrice.isEmpty() || OrderStatus.isEmpty()) {
                    JOptionPane.showMessageDialog(backPanel, "Please fill out the Customer details", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String Orderprice = txtOrderPrice.getText();

                if (!Orderprice.matches("^-?\\d+(\\.\\d+)?$")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid price", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                }

                String Orderstatus = txtOrderStatus.getText();

                if (Orderstatus.matches("^-?\\d+(\\.\\d+)?$")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Status", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                }

                try {
                    int generatedID = newCustomersdetails.addDatabase();

                    JOptionPane.showMessageDialog(backPanel, "Customer Order Added Successfully. Order ID: " + generatedID, "Success", JOptionPane.INFORMATION_MESSAGE);

                    txtcustomerid.setText(String.valueOf(generatedID));

                    txtCustomerName.setText(null);
                    txtCustomerEmail.setText(null);
                    txtCustomercontactNo.setText(null);
                    txtOrderDescription.setText(null);
                    txtOrderPrice.setText(null);
                    txtOrderStatus.setText(null);



                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(backPanel, "Failed to add Customer Order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        AddBtn.setPreferredSize(new Dimension(80, 60));
        AddBtn.setFont(new Font("Arial", Font.BOLD, 16));
        AddBtn.setForeground(Color.BLACK);
        AddBtn.setBackground(new java.awt.Color(175, 255, 175));

        AddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                AddBtn.setForeground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                AddBtn.setForeground(null);
            }
        });

        DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idText = txtcustomerid.getText();

                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(backPanel, "Please enter a valid Order ID", "Error", JOptionPane.ERROR_MESSAGE);

                }
                try {
                    int CustomerID = Integer.parseInt(idText);
                    Customersdetails order = new Customersdetails();
                    order.setCustomerID(CustomerID);

                    int result = JOptionPane.showConfirmDialog(backPanel, "Are you Sure you want to delete the Customer Order?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        order.deleteDatabase();

                        customercontroller.removeCustomerOrder(CustomerID);
                        Customersdetails customersdetailsToRemove = null;
                        for (Customersdetails newCustomersdetails : customerlist) {
                            if (newCustomersdetails.getCustomerID() == CustomerID) {
                                customersdetailsToRemove = newCustomersdetails;
                                break;
                            }
                        }
                        if (customersdetailsToRemove != null) {
                            customerlist.remove(customersdetailsToRemove);
                        }

                        JOptionPane.showMessageDialog(null, "Customer Order Deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        txtcustomerid.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer Order not Deleted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(backPanel, "Please enter a valid Order ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, "Error deleting Customer Order: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        DeleteBtn.setPreferredSize(new Dimension(200, 60));
        DeleteBtn.setFont(new Font("Arial", Font.BOLD, 16));
        DeleteBtn.setForeground(Color.BLACK);
        DeleteBtn.setBackground(new java.awt.Color(175, 255, 175));

        DeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                DeleteBtn.setForeground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                DeleteBtn.setForeground(null);
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Customer Update Form");
                customerUpdateForm window2 = new customerUpdateForm(customercontroller);
                window2.setVisible(true);
            }
        });

        updateBtn.setPreferredSize(new Dimension(200, 60));
        updateBtn.setFont(new Font("Arial", Font.BOLD, 16));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setBackground(new java.awt.Color(175, 255, 175));

        updateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                updateBtn.setForeground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                updateBtn.setForeground(null);
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerDetailsTable window3 = new customerDetailsTable(customercontroller);
                window3.setVisible(true);
            }
        });

        displayBtn.setPreferredSize(new Dimension(200, 60));
        displayBtn.setFont(new Font("Arial", Font.BOLD, 16));
        displayBtn.setForeground(Color.BLACK);
        displayBtn.setBackground(new java.awt.Color(175, 255, 175));


        displayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                displayBtn.setForeground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                displayBtn.setForeground(null);
            }
        });


        OrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You are Currently in the Customer Page !","customer Orders", 1);

                txtCustomerName.setText(null);
                txtCustomerEmail.setText(null);
                txtCustomercontactNo.setText(null);

                txtcustomerid.setText(null);
                txtOrderDescription.setText(null);
                txtOrderPrice.setText(null);
                txtOrderStatus.setText(null);

            }
        });
        OrderBtn.setPreferredSize(new Dimension(200, 60));
        OrderBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        OrderBtn.setForeground(Color.BLACK);
        OrderBtn.setBackground(new java.awt.Color(175, 255, 175));

        OrderBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                OrderBtn.setForeground(Color.RED);
                OrderBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                OrderBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                OrderBtn.setForeground(null);
            }
        });

        Suppliersbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SupplierForm ui = new SupplierForm();
                    ui.setContentPane(ui.BackPanel);
                    ui.setTitle("Supplier Management");
                    ui.setSize(1400, 1000);
                    ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    ui.setVisible(true);
            }
        });
        Suppliersbtn.setPreferredSize(new Dimension(200, 60));
        Suppliersbtn.setFont(new Font("Arial", Font.PLAIN, 16));
        Suppliersbtn.setForeground(Color.BLACK);
        Suppliersbtn.setBackground(new java.awt.Color(175, 255, 175));

        Suppliersbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                Suppliersbtn.setForeground(Color.RED);
                Suppliersbtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                Suppliersbtn.setFont(new Font("Arial", Font.PLAIN, 16));
                Suppliersbtn.setForeground(null);
            }
        });

        InventoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryForm ui = new InventoryForm();
                ui.setContentPane(ui.BackPanel);
                ui.setTitle("Item Management");
                ui.setSize(1400, 1000);
                ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ui.setVisible(true);
            }
        });
        InventoryBtn.setPreferredSize(new Dimension(240, 60));
        InventoryBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        InventoryBtn.setForeground(Color.BLACK);
        InventoryBtn.setBackground(new java.awt.Color(175, 255, 175));

        InventoryBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                InventoryBtn.setForeground(Color.RED);
                InventoryBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                InventoryBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                InventoryBtn.setForeground(null);
            }
        });

        EmployeesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        EmployeesBtn.setPreferredSize(new Dimension(100, 60));
        EmployeesBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        EmployeesBtn.setForeground(Color.BLACK);
        EmployeesBtn.setBackground(new java.awt.Color(175, 255, 175));

        EmployeesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                EmployeesBtn.setForeground(Color.RED);
                EmployeesBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                EmployeesBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                EmployeesBtn.setForeground(null);
            }
        });

        AssignJobsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        AssignJobsBtn.setPreferredSize(new Dimension(200, 60));
        AssignJobsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        AssignJobsBtn.setForeground(Color.BLACK);
        AssignJobsBtn.setBackground(new java.awt.Color(175, 255, 175));

        AssignJobsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                AssignJobsBtn.setForeground(Color.RED);
                AssignJobsBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                AssignJobsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                AssignJobsBtn.setForeground(null);
            }
        });

        ReportsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    DatabaseConnection_01 databaseConnector = new DatabaseConnection_01();
                    ReportService reportService = new ReportService(databaseConnector);
                    ReportController controller = new ReportController(reportService);
                    ReportView reportView = new ReportView(controller);
                    controller.connectToDatabase();
                    reportView.setVisible(true);
                });
            }
        });
        ReportsBtn.setPreferredSize(new Dimension(200, 60));
        ReportsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        ReportsBtn.setForeground(Color.BLACK);
        ReportsBtn.setBackground(new java.awt.Color(175, 255, 175));

        ReportsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                ReportsBtn.setForeground(Color.RED);
                ReportsBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                ReportsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                ReportsBtn.setForeground(null);
            }
        });


    }

    public static void main(String[] args) {
        customerOrderForm ui = new customerOrderForm();
        ui.setContentPane(ui.backPanel);
        ui.backPanel.setBackground(new java.awt.Color(175, 175, 225));
        ui.setTitle("customer Management");
        ui.setSize(1200, 700);
        ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ui.setVisible(true);
    }

}
