package ServiceLayer;

import DatabaseLayer.DatabaseConnection_02;
import Models.NotifyCustomer;
import Models.NotifyEmployee;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService
{
    private DatabaseConnection_02 singleConn;


    private Session newSession = null;
    private MimeMessage mimeMessage = null;
    private String fromUser;
    private String fromUserPassword;

    public MailService(String fromUser, String fromUserPassword) {
        this.fromUser = fromUser;
        this.fromUserPassword = fromUserPassword;
        setupServerProperties();
    }

    public void sendEmail(String[] emailRecipients, String emailSubject, String emailBody) throws MessagingException, IOException {
        mimeMessage = draftEmail(emailRecipients, emailSubject, emailBody);
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }

    private MimeMessage draftEmail(String[] emailRecipients, String emailSubject, String emailBody) throws AddressException, MessagingException, IOException {
        mimeMessage = new MimeMessage(newSession);

        for (String recipient : emailRecipients) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }
        mimeMessage.setSubject(emailSubject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody,"text/plain");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

    public MailService() {
        singleConn= DatabaseConnection_02.getSingleInstance();
    }

    public boolean addToCustomerNotify(NotifyCustomer notifyCustomer) {
        try {
            String sql = "INSERT INTO notifycustomer (email, subject, description) VALUES ('" + notifyCustomer.getCustomerEmail() + "', '" + notifyCustomer.getSubject() + "', '" + notifyCustomer.getBody() + "')";
            DatabaseConnection_02 singleConn = DatabaseConnection_02.getSingleInstance();
            return singleConn.ExecuteQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
            return false;
        }
    }

    public boolean addToEmployeeNotify(NotifyEmployee notifyEmployee) {
        try {
            String sql = "INSERT INTO notifyemployee (empID, empName, empEmail, subject, description) VALUES ('" + notifyEmployee.getEmpId() + "', '"+ notifyEmployee.getEmpName() + "', '"+ notifyEmployee.getEmpEmail() + "', '" + notifyEmployee.getSubject() + "', '" + notifyEmployee.getBody() + "')";
            DatabaseConnection_02 singleConn = DatabaseConnection_02.getSingleInstance();
            return singleConn.ExecuteQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
            return false;
        }
    }
}