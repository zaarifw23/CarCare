package Controllers;

import Models.Allocate;
import ServiceLayer.AllocateOrderService;

import java.util.List;

public class AllocateJobsController {
    AllocateOrderService service;

    public AllocateJobsController(){
        service = new AllocateOrderService();
    }

    public List<Allocate> getAllocations(){
        return service.getAllocations();
    }

    public boolean addAllocationToDatabase(Allocate allocate){
        return service.addAllocation(allocate);
    }

    public boolean updateAllocationInDatabase(Allocate allocate){
        return service.updateAllocation(allocate);
    }




    public boolean deleteAllocationFromDatabase(int orderId){
        return service.deleteAllocation(orderId);
    }



}
