package Views;

import Controllers.InventoryController;
import DatabaseLayer.DatabaseConnection_01;
import Models.Inventory;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ItemDetailsTable extends JFrame {
    private JPanel contentPane;
    private JTextField itemSearchField;
    private JButton searchBtn;
    private JTable ItemDetailsTable;
    private DefaultTableModel model;
    private boolean isFirstClick = true;
    private InventoryController inventoryController;

    public ItemDetailsTable(InventoryController controller) {
        this.inventoryController = controller;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 900);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        itemSearchField = new JTextField();
        itemSearchField.setBounds(298, 30, 600, 50);
        itemSearchField.setFont(new Font("Arial", Font.PLAIN, 16));
        itemSearchField.setForeground(Color.GRAY);
        itemSearchField.setText("Please enter an Item ID");
        contentPane.add(itemSearchField);

        itemSearchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (itemSearchField.getText().equals("Please enter an Item id")) {
                    itemSearchField.setText("");
                    itemSearchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (itemSearchField.getText().isEmpty()) {
                    itemSearchField.setForeground(Color.GRAY);
                    itemSearchField.setText("Please enter an Item id");
                }
            }
        });

        searchBtn = new JButton("Search Items");
        searchBtn.setBounds(900, 30, 250, 50);
        searchBtn.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(searchBtn);
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                searchBtn.setForeground(new java.awt.Color(0, 150, 0));
                searchBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                searchBtn.setFont(new Font("Arial", Font.BOLD, 16));
                searchBtn.setForeground(null);
            }
        });
        JTable itemDetailsTable = null;
        JTable finalItemDetailsTable = itemDetailsTable;
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String URL =  DatabaseConnection_01.getDatabaseURL() ;
                final String USERNAME = DatabaseConnection_01.getDatabaseUserName();
                final String PASSWORD = DatabaseConnection_01.getDatabasePassword();

                String inputText = itemSearchField.getText().trim();

                if (inputText.equals("Please enter an Item id")) {
                    JOptionPane.showMessageDialog(contentPane,"Please enter a valid Item id");
                    return;
                }

                int itemID = Integer.parseInt(inputText);

                String selectQuery = "SELECT * FROM Items WHERE ItemID = ?";

                try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                     PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

                    preparedStatement.setInt(1, itemID);

                    ResultSet rs = preparedStatement.executeQuery();

                    if (rs.next()) {
                        int foundItemID = rs.getInt("ItemID");
                        String itemName = rs.getString("ItemName");
                        String itemCategory = rs.getString("ItemCategory");
                        String itemPrice = rs.getString("ItemPrice");
                        String itemQuantity = rs.getString("ItemQuantity");

                        Inventory item = new Inventory(itemName, itemCategory, Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity));
                        item.setItemID(foundItemID);

                        model = new DefaultTableModel(new Object[][]{{item.getItemID(), item.getItemName(), item.getItemCategory(),
                                item.getItemPrice(), item.getItemQuantity()}},
                                new String[]{"ID", "Name", "Category", "Price", "Quantity"});
                        finalItemDetailsTable.setModel(model);
                    } else {
                        JOptionPane.showMessageDialog(contentPane,"Item Not Existing");
                        System.out.println("No Item found with ID: " + itemID);
                    }
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(contentPane, exception);
                }
            }
        });

        itemDetailsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(itemDetailsTable);
        scrollPane.setBounds(300, 100, 850, 650);
        contentPane.add(scrollPane);
        itemDetailsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JButton displayBtn = new JButton("Display Items");
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

        JTable finalItemDetailsTable1 = itemDetailsTable;
        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = inventoryController.getInventoryTableModel();
                finalItemDetailsTable1.setModel(model);
                isFirstClick = false;
                System.out.println("Displayed supplier details");
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
                InventoryForm mainWindow = new InventoryForm();
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
        InventoryController inventoryController = new InventoryController(new ArrayList<>());
        ItemDetailsTable ui = new ItemDetailsTable(inventoryController);
        ui.setTitle("Item Details Table");
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }
}