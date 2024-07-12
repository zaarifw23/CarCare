package Views;

import Controllers.customerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class customerUpdateForm extends  JFrame {

    JPanel contentPane;
    private customerController customercontroller;

    public customerUpdateForm(customerController customercontroller) {
        this.customercontroller = customercontroller;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblcustomerId = new JLabel("customer ID ");
        lblcustomerId.setBounds(80, 20, 100, 40);
        contentPane.add(lblcustomerId);
        lblcustomerId.setPreferredSize(new Dimension(200, 40));
        lblcustomerId.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField txtcustomerid = new JTextField();
        txtcustomerid.setBounds(220, 20, 280, 40);
        contentPane.add(txtcustomerid);
        txtcustomerid.setPreferredSize(new Dimension(200, 40));
        txtcustomerid.setFont(new Font("Arial", Font.BOLD, 16));
        txtcustomerid.setForeground(Color.BLUE);

        JLabel lblCustomerName = new JLabel("Customer Name ");
        lblCustomerName.setBounds(80, 80, 150, 40);
        contentPane.add(lblCustomerName);
        lblCustomerName.setPreferredSize(new Dimension(200, 40));
        lblCustomerName.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField txtCustomerName = new JTextField();
        txtCustomerName.setBounds(220, 80, 280, 40);
        contentPane.add(txtCustomerName);
        txtCustomerName.setPreferredSize(new Dimension(200, 40));
        txtCustomerName.setFont(new Font("Arial", Font.BOLD, 16));
        txtCustomerName.setForeground(Color.BLUE);

        JLabel lblCustomerEmail = new JLabel("Customer Email");
        lblCustomerEmail.setBounds(80, 140, 140, 40);
        contentPane.add(lblCustomerEmail);
        lblCustomerEmail.setPreferredSize(new Dimension(200, 40));
        lblCustomerEmail.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField txtCustomerEmail = new JTextField();
        txtCustomerEmail.setBounds(220, 140, 280, 40);
        contentPane.add(txtCustomerEmail);
        txtCustomerEmail.setPreferredSize(new Dimension(200, 40));
        txtCustomerEmail.setFont(new Font("Arial", Font.BOLD, 16));
        txtCustomerEmail.setForeground(Color.BLUE);

        JLabel lblCustomercontactNo = new JLabel("Customer Contact");
        lblCustomercontactNo.setBounds(80, 200, 140, 40);
        contentPane.add(lblCustomercontactNo);
        lblCustomercontactNo.setPreferredSize(new Dimension(200, 40));
        lblCustomercontactNo.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField txtCustomercontactNo = new JTextField();
        txtCustomercontactNo.setBounds(220, 200, 280, 40);
        contentPane.add(txtCustomercontactNo);
        txtCustomercontactNo.setPreferredSize(new Dimension(200, 40));
        txtCustomercontactNo.setFont(new Font("Arial", Font.BOLD, 16));
        txtCustomercontactNo.setForeground(Color.BLUE);

        JLabel lblOrderDescription = new JLabel("Order Description");
        lblOrderDescription.setBounds(80, 260, 140, 40);
        contentPane.add(lblOrderDescription);
        lblOrderDescription.setPreferredSize(new Dimension(200, 40));
        lblOrderDescription.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField txtOrderDescription = new JTextField();
        txtOrderDescription.setBounds(220, 260, 280, 40);
        contentPane.add(txtOrderDescription);
        txtOrderDescription.setPreferredSize(new Dimension(200, 40));
        txtOrderDescription.setFont(new Font("Arial", Font.BOLD, 16));
        txtOrderDescription.setForeground(Color.BLUE);

        JLabel lblOrderStatus = new JLabel("Order Status");
        lblOrderStatus.setBounds(80, 320, 140, 40);
        contentPane.add(lblOrderStatus);
        lblOrderStatus.setPreferredSize(new Dimension(200, 40));
        lblOrderStatus.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField txtOrderStatus = new JTextField();
        txtOrderStatus.setBounds(220, 320, 280, 40);
        contentPane.add(txtOrderStatus);
        txtOrderStatus.setPreferredSize(new Dimension(200, 40));
        txtOrderStatus.setFont(new Font("Arial", Font.BOLD, 16));
        txtOrderStatus.setForeground(Color.BLUE);

        JLabel lblOrderPrice = new JLabel("Order Price");
        lblOrderPrice.setBounds(80, 380, 140, 40);
        contentPane.add(lblOrderPrice);
        lblOrderPrice.setPreferredSize(new Dimension(200, 40));
        lblOrderPrice.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField txtOrderPrice = new JTextField();
        txtOrderPrice.setBounds(220, 380, 280, 40);
        contentPane.add(txtOrderPrice);
        txtOrderPrice.setPreferredSize(new Dimension(200, 40));
        txtOrderPrice.setFont(new Font("Arial", Font.BOLD, 16));
        txtOrderPrice.setForeground(Color.BLUE);






        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(100, 450, 200, 60);
        contentPane.add(updateBtn);
        updateBtn.setFont(new Font("Arial", Font.BOLD, 16));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setBackground(new java.awt.Color(175, 255, 175));

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CustomerID = txtcustomerid.getText();
                String CustomerName = txtCustomerName.getText();
                String CustomerEmail = txtCustomerEmail.getText();
                String CustomercontactNo = txtCustomercontactNo.getText();
                String OrderDescription = txtOrderDescription.getText();
                String OrderStatus = txtOrderStatus.getText();
                String OrderPrice = txtOrderPrice.getText();



                if (CustomerID == null) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter a valid Order ID", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        customercontroller.updateCustomer(Integer.parseInt(CustomerID), CustomerName, CustomerEmail, CustomercontactNo,OrderDescription,OrderStatus,OrderPrice);
                        JOptionPane.showMessageDialog(contentPane, "Customer Order details updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(contentPane, "Please enter a valid Order ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        JButton returnBtn = new JButton("Return");
        returnBtn.setBounds(320, 450, 200, 60);
        contentPane.add(returnBtn);
        returnBtn.setFont(new Font("Arial", Font.BOLD, 16));
        returnBtn.setForeground(Color.BLACK);
        returnBtn.setBackground(new java.awt.Color(175, 255, 175));

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Returned to the main page");
                dispose();
                customerOrderForm mainWindow = new customerOrderForm();
                mainWindow.setVisible(true);
            }
        });

        updateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateBtn.setForeground(Color.red);
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateBtn.setForeground(null);
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        });

        returnBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                returnBtn.setForeground(Color.RED);
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                returnBtn.setForeground(null);
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        });

    }


    public static void main(String[] args) {
        customerController customercontroller = new customerController(new ArrayList<>());
        customerUpdateForm ui = new customerUpdateForm(customercontroller);
        ui.setContentPane(ui.contentPane);
        ui.contentPane.setBackground(new java.awt.Color(255, 182, 193));
        ui.setTitle("Customer Management");
        ui.setSize(600, 500);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }

}