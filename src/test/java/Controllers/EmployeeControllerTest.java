package Controllers;

import Models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {

    static EmployeeController employeeController;
    static Employee employee;
    static ArrayList<Employee> employees;

    @BeforeAll
    public static void createEmployeeController() {
        employeeController = new EmployeeController();
    }

    @BeforeEach
    public void setEmployeeController() {
        employee = new Employee();
        employee.setEmpName("John");
        employee.setPosition("Manager");
        employee.setNic("123456789V");
        employee.setPhone("0712345678");
        employee.setAddress("No. 123, Galle Road, Colombo 03");

    }




}