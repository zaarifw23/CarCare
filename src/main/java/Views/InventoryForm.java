package Views;

import Controllers.InventoryController;
import Controllers.ReportController;
import DatabaseLayer.DatabaseConnection_01;
import Models.Inventory;
import ServiceLayer.ReportService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class InventoryForm extends JFrame {

    JPanel BackPanel;
    private JButton OrdersBtn;
    private JButton SuppliersBtn;
    private JButton InventoryBtn;
    private JButton EmployeesBtn;
    private JButton AssignJobsBtn;
    private JButton ReportsBtn;
    private JTextField ItemNameField;
    private JTextField ItemCategoryField;
    private JTextField ItemPriceField;
    private JTextField ItemQuantityField;
    private JButton DisplayTableBtn;
    private JButton UpdateBtn;
    private JTextField ItemIDField;
    private JLabel ItemNameLabel;
    private JLabel ItemCategoryLabel;
    private JLabel ItemPriceLabel;
    private JLabel ItemQuantityLabel;
    private JButton DeleteBtn;
    private JLabel ItemIDLabel;
    private JButton AddBtn;
    private InventoryController inventoryController;
    private final ArrayList<Inventory> itemList;
    int itemcount;
    private Inventory selectedItem;

    public InventoryForm() {
        itemList = new ArrayList<>();
        inventoryController = new InventoryController(itemList);

        ItemIDField.setFont(new Font("Arial", Font.PLAIN, 15));
        ItemIDField.setPreferredSize(new Dimension(200, 40));
        ItemIDLabel.setFont(new Font("Arial", Font.BOLD, 20));

        ItemNameField.setPreferredSize(new Dimension(200, 40));
        ItemNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemNameField.setFont(new Font("Arial", Font.PLAIN, 16));

        ItemCategoryField.setPreferredSize(new Dimension(200, 40));
        ItemCategoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemCategoryField.setFont(new Font("Arial", Font.PLAIN, 16));

        ItemPriceField.setPreferredSize(new Dimension(200, 40));
        ItemPriceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemPriceField.setFont(new Font("Arial", Font.PLAIN, 16));

        ItemQuantityField.setPreferredSize(new Dimension(200, 40));
        ItemQuantityLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ItemQuantityField.setFont(new Font("Arial", Font.PLAIN, 16));

        AddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ItemName = ItemNameField.getText();
                String ItemCategory = ItemCategoryField.getText();
                double ItemPrice = Double.parseDouble(ItemPriceField.getText());
                int ItemQuantity = Integer.parseInt(ItemQuantityField.getText());

                Inventory newItem = new Inventory(ItemName, ItemCategory, ItemPrice, ItemQuantity);

                if (ItemName.isEmpty() || ItemCategory.isEmpty() || ItemPrice <= 0 || ItemQuantity <= 0) {
                    JOptionPane.showMessageDialog(BackPanel, "Please fill out the Item details", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int generatedID = newItem.addToDatabase();

                    JOptionPane.showMessageDialog(BackPanel, "Item Added Successfully. Item ID: " + generatedID, "Success", JOptionPane.INFORMATION_MESSAGE);

                    ItemIDField.setText(String.valueOf(generatedID));

                    ItemNameField.setText(null);
                    ItemCategoryField.setText(null);
                    ItemPriceField.setText(null);
                    ItemQuantityField.setText(null);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(BackPanel, "Failed to add Item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        AddBtn.setPreferredSize(new Dimension(200, 60));
        AddBtn.setFont(new Font("Arial", Font.BOLD, 16));


        AddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                AddBtn.setForeground(new java.awt.Color(0, 150, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                AddBtn.setForeground(null);
            }
        });


        DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = ItemIDField.getText();

                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(BackPanel, "Please enter a valid Item ID", "Error", JOptionPane.ERROR_MESSAGE);

                }
                try {
                    int ItemID = Integer.parseInt(idText);
                    Inventory Item = new Inventory();
                    Item.setItemID(ItemID);

                    int result = JOptionPane.showConfirmDialog(BackPanel, "Are you Sure you want to delete the Item?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        Item.deleteFromDatabase();

                        inventoryController.removeItem(ItemID);
                        Inventory itemToRemove = null;
                        for (Inventory newItem : itemList) {
                            if (newItem.getItemID() == ItemID) {
                                itemToRemove = newItem;
                                break;
                            }
                        }
                        if (itemToRemove != null) {
                            itemList.remove(itemToRemove);
                        }

                        JOptionPane.showMessageDialog(null, "Item Deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        ItemIDField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Item not Deleted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(BackPanel, "Please enter a valid Item ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, "Error deleting Item: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        DeleteBtn.setPreferredSize(new Dimension(200, 60));
        DeleteBtn.setFont(new Font("Arial", Font.BOLD, 16));

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


        UpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Item Update Form");
                ItemUpdateForm window2 = new ItemUpdateForm(inventoryController);
                window2.setVisible(true);
            }
        });

        UpdateBtn.setPreferredSize(new Dimension(200, 60));
        UpdateBtn.setFont(new Font("Arial", Font.BOLD, 16));

        UpdateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                UpdateBtn.setForeground(new java.awt.Color(0, 150, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                UpdateBtn.setForeground(null);
            }
        });


        DisplayTableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemDetailsTable window3 = new ItemDetailsTable(inventoryController);
                window3.setVisible(true);
            }
        });

        DisplayTableBtn.setPreferredSize(new Dimension(200, 60));
        DisplayTableBtn.setFont(new Font("Arial", Font.BOLD, 16));

        DisplayTableBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                DisplayTableBtn.setForeground(new java.awt.Color(0, 150, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                DisplayTableBtn.setForeground(null);
            }
        });


        OrdersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        OrdersBtn.setPreferredSize(new Dimension(200, 60));
        OrdersBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        OrdersBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                OrdersBtn.setForeground(new java.awt.Color(0, 150, 0));
                OrdersBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                OrdersBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                OrdersBtn.setForeground(null);
            }
        });


        SuppliersBtn.addActionListener(new ActionListener() {
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
        SuppliersBtn.setPreferredSize(new Dimension(200, 60));
        SuppliersBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        SuppliersBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                SuppliersBtn.setForeground(new java.awt.Color(0, 150, 0));
                SuppliersBtn.setFont(new Font("Arial", Font.BOLD, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                SuppliersBtn.setFont(new Font("Arial", Font.PLAIN, 16));
                SuppliersBtn.setForeground(null);
            }
        });


        InventoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You are Currently in the Item Management Page!","Manage Items", 1);

                ItemNameField.setText(null);
                ItemCategoryLabel.setText(null);
                ItemPriceField.setText(null);
                ItemQuantityField.setText(null);
                ItemIDField.setText(null);

            }
        });


        EmployeesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        EmployeesBtn.setPreferredSize(new Dimension(200, 60));
        EmployeesBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        EmployeesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                EmployeesBtn.setForeground(new java.awt.Color(0, 150, 0));
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
        AssignJobsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                AssignJobsBtn.setForeground(new java.awt.Color(0, 150, 0));
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
        ReportsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                ReportsBtn.setForeground(new java.awt.Color(0, 150, 0));
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
        InventoryForm ui = new InventoryForm();
        ui.setContentPane(ui.BackPanel);
        ui.setTitle("Item Management");
        ui.setSize(1400, 1000);
        ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ui.setVisible(true);
    }
}
