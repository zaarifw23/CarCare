package Controllers;

import Models.Employee;
import ServiceLayer.EmployeeService;

import java.util.List;

public class EmployeeController {
    EmployeeService service;
    Employee employee;

    public EmployeeController() {
        service = new EmployeeService();
    }

    public Employee addEmployee(int empID, String firstName, String position, String nic, String phone, String email, String address, String salary) {
        employee = new Employee(empID, firstName, position, nic, phone, email, address, salary);
        return employee;
    }



    public Employee findEmployeeById(int empID) {
        return service.getEmployeeById(empID);
    }

    public List<Employee> findEmployeesByName(String name) {
        return service.getEmployeeByName(name);
    }

    public List<Employee> findAllEmployees() {
        return service.getAllEmployees();
    }



    public boolean addEmployeeToDatabase(Employee employee)
    {
        return service.addEmployee(employee);
    }

    public boolean updateEmployeeInDatabase(Employee employee)
    {
        return service.updateEmployee(employee);
    }

    public boolean deleteEmployeeFromDatabase(int empID)
    {
        return service.deleteEmployee(empID);
    }


}
