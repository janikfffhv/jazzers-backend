package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.backend.ServiceRegistry;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.dto.CustomerDetailDTO;
import at.fhv.jazzers.shared.dto.CustomerOverviewDTO;

import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

@Stateless
public class RMI_CustomerServiceImpl implements RMI_CustomerService {
    private RMI_CustomerService rmi_customerService = ServiceRegistry.rmi_customerService();

    public RMI_CustomerServiceImpl() {

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