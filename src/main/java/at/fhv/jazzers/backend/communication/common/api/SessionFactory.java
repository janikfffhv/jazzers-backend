package at.fhv.jazzers.backend.communication.common.api;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface SessionFactory {
    Session authenticate(String username, String password) throws MalformedURLException, NotBoundException, RemoteException;
}
