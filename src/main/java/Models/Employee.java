package Models;

public class Employee {
    private int empID;
    private String empName;
    private String position;
    private String nic;
    private String phone;
    private String email;
    private String address;
    private String salary;

    public Employee() {
    }

    public Employee(String empName, String position, String nic, String phone, String address, String email, String salary) {
        this.empName = empName;
        this.position = position;
        this.nic = nic;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
    }

    public Employee(int empID, String empName, String position, String nic, String phone, String address, String email, String salary) {
        this.empID = empID;
        this.empName = empName;
        this.position = position;
        this.nic = nic;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", firstName='" + empName + '\'' +
                ", position='" + position + '\'' +
                ", nic='" + nic + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }


    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }


    public String getPosition() {
        return position;
    }

    public String getNic() {
        return nic;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getSalary() {
        return salary;
    }
}
