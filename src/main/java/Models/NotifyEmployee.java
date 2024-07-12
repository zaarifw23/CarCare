package Models;

public class NotifyEmployee {

        String empId;
        String empName;
        String empEmail;
        String subject;
        String body;

    public NotifyEmployee(String empId, String empName, String empEmail, String subject, String body) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.subject = subject;
        this.body = body;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
