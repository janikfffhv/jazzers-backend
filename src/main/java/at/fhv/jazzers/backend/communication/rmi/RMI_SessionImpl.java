package at.fhv.jazzers.backend.communication.rmi;

import at.fhv.jazzers.backend.communication.common.api.Session;
import at.fhv.jazzers.shared.api.RMI_CustomerService;
import at.fhv.jazzers.shared.api.RMI_ProductService;
import at.fhv.jazzers.shared.api.RMI_SaleService;
import at.fhv.jazzers.shared.api.RMI_Session;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_SessionImpl extends UnicastRemoteObject implements RMI_Session {
    private String username;
    private RMI_CustomerService rmi_customerService;
    private RMI_ProductService rmi_productService;
    private RMI_SaleService rmi_saleService;

    public RMI_SessionImpl(Session session) throws RemoteException {
        super(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));

        username = session.username();

        try {
            rmi_customerService = session.rmi_customerService();
        } catch (Exception ignored) { }

        try {
            rmi_productService = new RMI_ProductServiceImpl(session.productService());
        } catch (Exception ignored) { }

        try {
            rmi_saleService = new RMI_SaleServiceImpl(session.saleService());
        } catch (Exception ignored) { }
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public RMI_CustomerService rmi_customerService() {
        if (rmi_customerService == null) {
            throw new IllegalArgumentException("Insufficient permissions.");
        }
        return rmi_customerService;
    }

    @Override
    public RMI_ProductService rmi_productService() {
        if (rmi_productService == null) {
            throw new IllegalArgumentException("Insufficient permissions.");
        }
        return rmi_productService;
    }

    @Override
    public RMI_SaleService rmi_saleService() {
        if (rmi_saleService == null) {
            throw new IllegalArgumentException("Insufficient permissions.");
        }
        return rmi_saleService;
    }
}
