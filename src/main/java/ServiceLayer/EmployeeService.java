package ServiceLayer;

import DatabaseLayer.DatabaseConnection_02;
import Models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private DatabaseConnection_02 singleConn;
    public EmployeeService()
    {
        singleConn= DatabaseConnection_02.getSingleInstance();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "SELECT * FROM employees";
            ResultSet rs = singleConn.ExecuteSelectQuery(query);
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("empID"),
                        rs.getString("empName"),
                        rs.getString("empPosition"),
                        rs.getString("empNIC"),
                        rs.getString("empPhone"),
                        rs.getString("empAddress"),
                        rs.getString("empEmail"),
                        rs.getString("empSalary")
                );
                employees.add(employee);
            }
        } catch (SQLException ex) {
            System.out.println("Cannot fetch employees: " + ex.getMessage());
        }
        return employees;
    }


    public Employee getEmployeeById(int empID) {
        Employee employee = null;
        try {
            String query = "SELECT * FROM employees WHERE empID='" + empID + "'";
            ResultSet rs = singleConn.ExecuteSelectQuery(query);
            while (rs.next()) {
                employee = new Employee(
                        rs.getInt("empID"),
                        rs.getString("empName"),
                        rs.getString("empPosition"),
                        rs.getString("empNIC"),
                        rs.getString("empPhone"),
                        rs.getString("empAddress"),
                        rs.getString("empEmail"),
                        rs.getString("empSalary")
                );
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Cannot fetch employees: " + ex.getMessage());
        }
        return employee;
    }


    public List<Employee> getEmployeeByName(String empName) {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "SELECT * FROM employees WHERE empName LIKE '%" + empName + "%'";
            ResultSet rs = singleConn.ExecuteSelectQuery(query);
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("empID"),
                        rs.getString("empName"),
                        rs.getString("empPosition"),
                        rs.getString("empNIC"),
                        rs.getString("empPhone"),
                        rs.getString("empAddress"),
                        rs.getString("empEmail"),
                        rs.getString("empSalary")
                );
                employees.add(employee);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Cannot fetch employees: " + ex.getMessage());
        }
        return employees;
    }



    public boolean addEmployee(Employee employee)
    {
        try
        {
            String query="INSERT INTO employees (empName, empPosition, empNIC, empPhone, empAddress, empEmail, empSalary) VALUES ('"+employee.getEmpName()+"','"+employee.getPosition()+"','"+employee.getNic()+"','"+employee.getPhone()+"','"+employee.getAddress()+"','"+employee.getEmail()+"','"+employee.getSalary()+"')";
            return singleConn.ExecuteQuery(query);
        }catch (Exception ex)
        {
          System.out.println("Cannot insert a employee");
          return false;
        }
    }

    public boolean updateEmployee(Employee employee)
    {
        try
        {
            String query="UPDATE employees SET empName='"+employee.getEmpName()+"',empPosition='"+employee.getPosition()+"',empNIC='"+employee.getNic()+"',empPhone='"+employee.getPhone()+"',empAddress='"+employee.getAddress()+"',empEmail='"+employee.getEmail()+"',empSalary='"+employee.getSalary()+"' WHERE empID='"+employee.getEmpID()+"'";
            System.out.println(query);
            return singleConn.ExecuteQuery(query);
        }catch (Exception ex)
        {
          System.out.println("Cannot update a employee");
          return false;
        }
    }

    public boolean deleteEmployee(int empID)
    {
        try
        {
            String query="DELETE FROM employees WHERE empID='"+empID+"'";
            return singleConn.ExecuteQuery(query);
        }catch (Exception ex)
        {
          System.out.println("Cannot delete a employee");
          return false;
        }
    }
}
