package Views;

import Controllers.customerController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class customerDetailsTable extends JFrame {

    private JPanel contentPane;


    private JTable customerDetailsTable;
    private DefaultTableModel model;
    private boolean isFirstClick = true;
    private customerController customercontroller;


    public customerDetailsTable(customerController controller){
        this.customercontroller = controller;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 900);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        customerDetailsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(customerDetailsTable);
        scrollPane.setBounds(300, 100, 850, 650);
        contentPane.add(scrollPane);
        customerDetailsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JButton displayBtn = new JButton("Display Customers");
        displayBtn.setBounds(25, 300, 250, 60);
        contentPane.add(displayBtn);
        displayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                displayBtn.setForeground(new java.awt.Color(0, 150, 0));
                displayBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                displayBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                displayBtn.setForeground(null);
            }
        });


        displayBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                displayBtn.setForeground(new java.awt.Color(0, 150, 0));
            }


            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                displayBtn.setForeground(null);
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = customercontroller.getCustomerTableModel();
                customerDetailsTable.setModel(model);
                isFirstClick = false;
                System.out.println("Displayed customer details");
            }
        });

        JButton returnBtn = new JButton("Return");
        returnBtn.setBounds(25, 400, 250, 60);
        contentPane.add(returnBtn);
        returnBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                returnBtn.setForeground(new java.awt.Color(0, 150, 0));
                returnBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }


            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                returnBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                returnBtn.setForeground(null);
            }
        });


        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Returned to main page");
                dispose();
                customerOrderForm mainWindow = new customerOrderForm();
                mainWindow.setVisible(true);
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
        customerController customercontroller  = new customerController(new ArrayList<>());
        customerDetailsTable ui = new customerDetailsTable(customercontroller);
        ui.setTitle("Customer Details Table");
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }
}
