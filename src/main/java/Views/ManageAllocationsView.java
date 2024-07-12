package Views;

import Controllers.AllocateJobsController;
import Controllers.EmployeeController;
import Controllers.customerController;
import Models.Allocate;
import Models.Customersdetails;
import Models.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAllocationsView extends JFrame {
    JPanel panel1;
    private JTextField txtOrderId;
    private JTextField txtEmpId;
    private JLabel lblOrderId;
    private JLabel lblEmpId;
    private JButton btnAssign;
    private JButton btnDeleteAllocation;
    private JTextField txtDeleteOrderId;
    private JButton updateBtn;
    private JButton saveButton;



    public ManageAllocationsView() {
        saveButton.setVisible(false);
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBtn.setEnabled(false);
                btnAssign.setEnabled(false);
                saveButton.setVisible(true);
                txtDeleteOrderId.setEditable(false);
                btnDeleteAllocation.setEnabled(false);
            }
        });

        btnAssign.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeController employeeController = new EmployeeController();
                AllocateJobsController allocateJobsController = new AllocateJobsController();

                int orderId = Integer.parseInt(txtOrderId.getText());
                int empId = Integer.parseInt(txtEmpId.getText());

                Employee employee = employeeController.findEmployeeById(empId);
                String empName = employee.getEmpName();

                Allocate allocate = new Allocate(orderId, empId, empName);

                if (allocateJobsController.addAllocationToDatabase(allocate)){
                    JOptionPane.showMessageDialog(null, "Allocated job successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to allocate job");
                }

            }
        });

        btnDeleteAllocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderId = Integer.parseInt(txtDeleteOrderId.getText());
                AllocateJobsController allocateJobsController = new AllocateJobsController();
                if (allocateJobsController.deleteAllocationFromDatabase(orderId)){
                    JOptionPane.showMessageDialog(null, "Allocation deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete allocation");
                }
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int orderId = Integer.parseInt(txtOrderId.getText());
                int empId = Integer.parseInt(txtEmpId.getText());

                EmployeeController employeeController = new EmployeeController();
                customerController customercontroller = new customerController();

            }
        });
    }



    public static void main(String[] args) {
//        JFrame frame = new JFrame("AllocationView");
//        frame.setContentPane(new ManageAllocationsView().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }
}




