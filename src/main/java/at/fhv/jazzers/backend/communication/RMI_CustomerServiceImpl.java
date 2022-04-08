package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.CustomerOverviewDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMI_CustomerServiceImpl extends UnicastRemoteObject implements RMI_CustomerService {
    protected RMI_CustomerServiceImpl() throws RemoteException {
        super(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));
    }

    @Override
    public List<CustomerOverviewDTO> search(String name) throws RemoteException {
        // weiterleiten an Customer-Backend
        return null;
    }
}


//             // TEST FOR:     LOCAL-Docker  <--RMI-->  LOCAL-Docker
//            RMI_CustomerService customerService = (RMI_CustomerService) Naming.lookup("rmi://10.0.40.164:1100/customerService");
//            customerService.search("max").forEach(customer -> System.out.println("Customer: " + customer.getFirstName()));