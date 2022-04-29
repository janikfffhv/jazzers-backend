package at.fhv.jazzers.backend.communication.rmi;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.CustomerDetailDTO;
import at.fhv.jazzers.shared.dto.CustomerOverviewDTO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RMI_CustomerServiceImpl extends UnicastRemoteObject implements RMI_CustomerService {
    RMI_CustomerService rmi_customerService = ServiceRegistry.rmi_customerService();

    public RMI_CustomerServiceImpl() throws RemoteException {
        super(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));
    }

    @Override
    public CustomerDetailDTO searchById(UUID customerId) throws RemoteException {
        return rmi_customerService.searchById(customerId);
    }

    @Override
    public List<CustomerOverviewDTO> searchByName(String name) throws RemoteException {
        return rmi_customerService.searchByName(name);
    }
}