package at.fhv.jazzers.communication;

import at.fhv.jazzers.application.dto.ProductOverviewDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// ToDo: Da wir noch kein Shared Folder haben, muss das Frontend 1:1 Code und Package gleich haben wie die Interfaces und DTOs
public interface ProductServiceRMI extends Remote {
    List<ProductOverviewDTO> searchByTitleOrInterpret(String searchString) throws RemoteException;
}