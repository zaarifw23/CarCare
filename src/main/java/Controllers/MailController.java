package Controllers;

import javax.mail.MessagingException;
import java.io.IOException;

import Models.NotifyCustomer;
import Models.NotifyEmployee;
import ServiceLayer.MailService;

public class MailController {
    MailService mailService = new MailService();
    public boolean sendEmail(String fromUser, String fromUserPassword, String[] recipients, String subject, String body) {
        try {
            MailService mail = new MailService(fromUser, fromUserPassword);
            mail.sendEmail(recipients, subject, body);
            return true;
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addToCustomerNotify(NotifyCustomer notifyCustomer) {
        return mailService.addToCustomerNotify(notifyCustomer);
    }

    public boolean addToEmployeeNotify(NotifyEmployee notifyEmployee) {
        return mailService.addToEmployeeNotify(notifyEmployee);
    }


}