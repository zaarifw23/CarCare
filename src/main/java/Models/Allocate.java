package Models;

public class Allocate {
    private int orderId;
    private int empId;
    private String empName;

    public Allocate(int orderId, int empId, String empName) {
        this.orderId = orderId;
        this.empId = empId;
        this.empName = empName;
    }

    private String orderDescription;
    private String allocatedDate;

    public void setAllocatedDate(String allocatedDate) {
        this.allocatedDate = allocatedDate;
    }


    public Allocate() {

    }


    @Override
    public String toString() {
        return "Allocate{" +
                "orderId=" + orderId +
                ", empId=" + empId +
                ", empName='" + empName + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", allocatedDate=" + allocatedDate +
                '}';
    }


    public Allocate(int orderId, int empId, String empName, String orderDescription, String allocatedDate) {
        this.orderId = orderId;
        this.empId = empId;
        this.empName = empName;
        this.orderDescription = orderDescription;
        this.allocatedDate = allocatedDate;
    }

    public Allocate(int orderId, int empId, String empName, String orderDescription) {
        this.orderId = orderId;
        this.empId = empId;
        this.empName = empName;
        this.orderDescription = orderDescription;
    }

    public String getAllocatedDate() {
        return allocatedDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }



    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

}
