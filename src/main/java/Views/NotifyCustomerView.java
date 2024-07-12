package Views;

import Controllers.MailController;
import Models.NotifyCustomer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotifyCustomerView extends JFrame {
    private JTextField txtDescription;
    private JTextField txtEmail;
    private JTextField txtSubject;
    private JLabel lblEmail;
    private JButton sendButton;
    JPanel panel1;

    public NotifyCustomerView() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MailController mailController = new MailController();
                String email = txtEmail.getText();
                String subject = txtSubject.getText();
                String description = txtDescription.getText();
                String recipients[] = new String[]{email};

                if (email.isEmpty() || subject.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                } else {
                    if (mailController.sendEmail("yourMail@gmail.com", "yourPassword", recipients, subject, description)) {
                        JOptionPane.showMessageDialog(null, "Email sent successfully");
                        NotifyCustomer notifyCustomer = new NotifyCustomer(email, subject, description);
                        mailController.addToCustomerNotify(notifyCustomer);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error sending email");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Notify Customer");
        frame.setContentPane(new NotifyCustomerView().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
