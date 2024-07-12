package Views;

import Controllers.EmployeeController;
import Controllers.MailController;
import Models.Employee;
import Models.NotifyEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotifyEmployeeForm extends JFrame{
    private JTextField txtSubject;
    private JTextField txtDescription;
    private JButton btnSend;
    private JTextField txtID;
    JPanel panel1;

    public NotifyEmployeeForm() {
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = txtSubject.getText();
                String description = txtDescription.getText();
                String id = txtID.getText();

                EmployeeController employeeController = new EmployeeController();
                Employee employee = employeeController.findEmployeeById(Integer.parseInt(id));

                String recipients[] = new String[]{employee.getEmail()};


                NotifyEmployee notifyEmployee = new NotifyEmployee(id, employee.getEmpName(), employee.getEmail(), subject, description);


                description = "Dear " + employee.getEmpName() + ",\n\n" + description;

                if (subject.isEmpty() || description.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                } else {
                    MailController mailController = new MailController();
                    if (mailController.sendEmail("yourEmail@gmail.com", "yourPassword", recipients, subject, description)) {
                        JOptionPane.showMessageDialog(null, "Email sent successfully");
                        mailController.addToEmployeeNotify(notifyEmployee);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error sending email");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Notify Customer");
        frame.setContentPane(new NotifyEmployeeForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
