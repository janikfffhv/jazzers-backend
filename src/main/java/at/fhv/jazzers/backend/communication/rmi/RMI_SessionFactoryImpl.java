package at.fhv.jazzers.backend.communication.rmi;

import at.fhv.jazzers.backend.communication.common.api.Session;
import at.fhv.jazzers.backend.communication.common.api.SessionFactory;
import at.fhv.jazzers.shared.api.RMI_Session;
import at.fhv.jazzers.shared.api.RMI_SessionFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_SessionFactoryImpl extends UnicastRemoteObject implements RMI_SessionFactory {
    private final SessionFactory sessionFactory;

    public RMI_SessionFactoryImpl(SessionFactory sessionFactory) throws RemoteException {
        super(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));
        this.sessionFactory = sessionFactory;
    }

    @Override
    public RMI_Session authenticate(String username, String password) throws RemoteException {
        try {
            Session session = sessionFactory.authenticate(username, password);
            return new RMI_SessionImpl(session);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
