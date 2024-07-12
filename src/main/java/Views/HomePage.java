package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JButton btnOrders;
    private JButton inventoryButton1;
    private JButton suppliersButton;
    private JButton allocateJobsButton;
    private JButton inventoryButton;
    private JButton notifyCustomersButton;
    private JButton employeesButton;
    private JButton notifyEmployeesButton;
    private JPanel panel1;
    private JButton reportsButton;

    public HomePage() {
        btnOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerOrderForm ui = new customerOrderForm();
                ui.setContentPane(ui.backPanel);
                ui.backPanel.setBackground(new java.awt.Color(175, 175, 225));
                ui.setTitle("customer Management");
                ui.setSize(1200, 700);
                ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ui.setVisible(true);
            }
        });
        suppliersButton.addActionListener(new ActionListener() {
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
        allocateJobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllocationsView allocationsView = new AllocationsView();
                allocationsView.setVisible(true);
            }
        });
        inventoryButton.addActionListener(new ActionListener() {
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
        notifyCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Notify Customer");
                frame.setContentPane(new NotifyCustomerView().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        employeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeView employeeView = new EmployeeView();
                employeeView.setVisible(true);
            }
        });
        notifyEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Notify Customer");
                frame.setContentPane(new NotifyEmployeeForm().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportView.main(null);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HomePage");
        frame.setContentPane(new HomePage().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
