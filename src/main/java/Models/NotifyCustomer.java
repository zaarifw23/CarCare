package Models;

public class NotifyCustomer {

    String customerEmail;
    String subject;
    String body;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public NotifyCustomer(String customerEmail, String subject, String body) {
        this.customerEmail = customerEmail;
        this.subject = subject;
        this.body = body;
    }

    public NotifyCustomer() {

    }



}
