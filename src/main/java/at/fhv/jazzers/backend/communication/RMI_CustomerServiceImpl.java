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
