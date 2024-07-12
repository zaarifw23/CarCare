package ServiceLayer;
import DatabaseLayer.DatabaseConnection_02;
import Models.Allocate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AllocateOrderService {

    private DatabaseConnection_02 singleConn;

    public AllocateOrderService(){
        singleConn = DatabaseConnection_02.getSingleInstance();
    }

    public boolean addAllocation(Allocate allocate){
        try{

            List<Allocate> allocations = getAllocations();

            for (Allocate existingAllocation : allocations) {
                if (existingAllocation.getOrderId() == allocate.getOrderId()) {
                    System.out.println("Error: OrderId already exists.");
                    return false;
                }
            }

            String insertQuery = "INSERT INTO allocate_jobs(orderId, empId, empName) VALUES("
                    + allocate.getOrderId() + ","
                    + allocate.getEmpId() + ","
                    + "'" + allocate.getEmpName() + "');";
            return singleConn.ExecuteQuery(insertQuery);
        } catch(Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return false;
        }
    }

    public List<Allocate> getAllocations(){
        List<Allocate> allocations = new ArrayList<>();
        try{
            String query = "SELECT * FROM allocate_jobs";
            ResultSet result =  singleConn.ExecuteSelectQuery(query);
            while(result.next()){
                Allocate allocate = new Allocate();
                allocate.setOrderId(result.getInt("orderId"));
                allocate.setEmpId(result.getInt("empId"));
                allocate.setEmpName(result.getString("empName"));
//                allocate.setOrderDescription(result.getString("orderDescription"));
                allocate.setAllocatedDate(result.getString("allocatedDate"));
                allocations.add(allocate);
            }
        } catch(Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
        }
        return allocations;
    }

    public boolean deleteAllocation(int orderId){
        try{
            String query = "DELETE FROM allocate_jobs WHERE orderId = " + orderId + ";";
            return singleConn.ExecuteQuery(query);
        } catch(Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return false;
        }
    }

    public boolean updateAllocation(Allocate allocate){
        try{

            List<Allocate> allocations = getAllocations();
            boolean status = false;

            for (Allocate existingAllocation : allocations) {
                if (existingAllocation.getOrderId() == allocate.getOrderId()) {
                    status = true;
                    break;
                }
            }

            if (!status) {
                System.out.println("Error: OrderId does not exist.");
                return false;
            }

            String query = "UPDATE allocate_jobs SET "
                    + "empId = " + allocate.getEmpId() + ","
                    + "empName = '" + allocate.getEmpName() + "',"
                    + "orderDescription = '" + allocate.getOrderDescription() + "',"
                    + "allocatedDate = CURRENT_TIMESTAMP"
                    + " WHERE orderId = " + allocate.getOrderId() + ";";
            return singleConn.ExecuteQuery(query);
        } catch(Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return false;
        }
    }




}
