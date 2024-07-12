package Views;

import Controllers.InventoryController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ItemUpdateForm extends JFrame {
    private JPanel contentPane;
    private InventoryController inventoryController;
    public ItemUpdateForm(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel itemIDLabel = new JLabel("Item ID ");
        itemIDLabel.setBounds(80, 20, 100, 40);
        contentPane.add(itemIDLabel);
        itemIDLabel.setPreferredSize(new Dimension(200, 40));
        itemIDLabel.setFont( new Font("Arial", Font.PLAIN, 16));

        JTextField itemIDField = new JTextField();
        itemIDField.setBounds(220, 20, 280, 40);
        contentPane.add(itemIDField);
        itemIDField.setPreferredSize(new Dimension(200, 40));
        itemIDField.setFont( new Font("Arial", Font.PLAIN, 16));


        JLabel itemNameLabel = new JLabel("Item Name ");
        itemNameLabel.setBounds(80, 80, 150, 40);
        contentPane.add(itemNameLabel);
        itemNameLabel.setPreferredSize(new Dimension(200, 40));
        itemNameLabel.setFont( new Font("Arial", Font.PLAIN, 16));

        JTextField itemNameField = new JTextField();
        itemNameField.setBounds(220, 80, 280, 40);
        contentPane.add(itemNameField);
        itemNameField.setPreferredSize(new Dimension(200, 40));
        itemNameField.setFont( new Font("Arial", Font.PLAIN, 16));

        JLabel itemCategoryLabel = new JLabel("Item Category ");
        itemCategoryLabel.setBounds(80, 140, 140, 40);
        contentPane.add(itemCategoryLabel);
        itemCategoryLabel.setPreferredSize(new Dimension(200, 40));
        itemCategoryLabel.setFont( new Font("Arial", Font.PLAIN, 16));

        JTextField itemCategoryField = new JTextField();
        itemCategoryField.setBounds(220, 140, 280, 40);
        contentPane.add(itemCategoryField);
        itemCategoryField.setPreferredSize(new Dimension(200, 40));
        itemCategoryField.setFont( new Font("Arial", Font.PLAIN, 16));

        JLabel itemPriceLabel = new JLabel("Item Price ");
        itemPriceLabel.setBounds(80, 200, 140, 40);
        contentPane.add(itemPriceLabel);
        itemPriceLabel.setPreferredSize(new Dimension(200, 40));
        itemPriceLabel.setFont( new Font("Arial", Font.PLAIN, 16));

        JTextField itemPriceField = new JTextField();
        itemPriceField.setBounds(220, 200, 280, 40);
        contentPane.add(itemPriceField);
        itemPriceField.setPreferredSize(new Dimension(200, 40));
        itemPriceField.setFont( new Font("Arial", Font.PLAIN, 16));

        JLabel itemQuantityLabel = new JLabel("Item Quantity ");
        itemQuantityLabel.setBounds(80, 260, 140, 40);
        contentPane.add(itemQuantityLabel);
        itemQuantityLabel.setPreferredSize(new Dimension(200, 40));
        itemQuantityLabel.setFont( new Font("Arial", Font.PLAIN, 16));

        JTextField itemQuantityField = new JTextField();
        itemQuantityField.setBounds(220, 260, 280, 40);
        contentPane.add(itemQuantityField);
        itemQuantityField.setPreferredSize(new Dimension(200, 40));
        itemQuantityField.setFont( new Font("Arial", Font.PLAIN, 16));

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(80, 400, 200, 50);
        contentPane.add(updateBtn);
        updateBtn.setFont( new Font("Arial", Font.BOLD, 16));

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemID = itemNameField.getText();
                String itemName = itemNameField.getText();
                String itemCategory = itemCategoryField.getText();
                String itemPrice = itemPriceField.getText();
                String itemQuantity = itemQuantityField.getText();

                if (itemName.isEmpty() || itemCategory.isEmpty() || itemPrice.isEmpty() || itemQuantity.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Please fill out the Item details", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (itemID.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter a valid Item ID", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        inventoryController.updateItem(Integer.parseInt(itemID), itemName, itemCategory, Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity));
                        JOptionPane.showMessageDialog(contentPane, "Item details updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(contentPane, "Please enter a valid Item ID", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(contentPane, "Error updating Item: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton returnBtn = new JButton("Return");
        returnBtn.setBounds(300, 400, 200, 50);
        contentPane.add(returnBtn);
        returnBtn.setFont( new Font("Arial", Font.BOLD, 16));

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Returned to the main page");
                dispose();
                InventoryForm mainWindow = new InventoryForm();
                mainWindow.setVisible(true);
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemID = itemIDField.getText();
                String itemName = itemNameField.getText();
                String itemCategory = itemCategoryField.getText();
                String itemPrice = itemPriceField.getText();
                String itemQuantity = itemQuantityField.getText();

                if (itemName.isEmpty() || itemCategory.isEmpty() || itemPrice.isEmpty() || itemQuantity.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Please fill out all the Item details", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int parsedItemID = Integer.parseInt(itemID);

                    if (parsedItemID <= 0) {
                        JOptionPane.showMessageDialog(contentPane, "Please enter a valid Item ID", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    inventoryController.updateItem(parsedItemID, itemName, itemCategory, Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity));

                    JOptionPane.showMessageDialog(contentPane, "Item details updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter a valid Item ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
        ItemUpdateForm ui = new ItemUpdateForm(inventoryController);
        ui.setContentPane(ui.contentPane);
        ui.setTitle("Item Management");
        ui.setSize(600, 500);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }
}