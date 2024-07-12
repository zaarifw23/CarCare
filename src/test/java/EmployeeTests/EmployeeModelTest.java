package EmployeeTests;

import Models.Employee;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeModelTest {

    static Employee employee;

    @BeforeAll
    public static void createEmployee(){
        employee = new Employee();
    }

    @BeforeEach
    public void setEmployee() {
        employee.setEmpName("John");
        employee.setPosition("Manager");
        employee.setNic("123456789V");
        employee.setPhone("0712345678");
        employee.setAddress("No. 123, Galle Road, Colombo 03");
        employee.setEmail("John@gmail.com");
    }

    @Test
    public void testEmployee() {
        assertEquals("John", employee.getEmpName());
        assertEquals("Manager", employee.getPosition());
        assertEquals("123456789V", employee.getNic());
        assertEquals("0712345678", employee.getPhone());
        assertEquals("No. 123, Galle Road, Colombo 03", employee.getAddress());
        assertEquals("John@gmail.com", employee.getEmail());
    }

    @AfterEach
    public void cleanData() {
        employee.setEmpName(null);
        employee.setPosition(null);
        employee.setNic(null);
        employee.setPhone(null);
        employee.setAddress(null);
        employee.setEmail(null);
    }

    @AfterAll
    public static void deleteEmployee() {
        employee = null;
    }
}