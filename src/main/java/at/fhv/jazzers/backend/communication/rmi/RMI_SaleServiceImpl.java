package at.fhv.jazzers.backend.communication.rmi;

import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.shared.api.RMI_SaleService;
import at.fhv.jazzers.shared.dto.LineDTO;
import at.fhv.jazzers.shared.dto.SaleHistoryEntryDetailDTO;
import at.fhv.jazzers.shared.dto.SaleHistoryEntryOverviewDTO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class RMI_SaleServiceImpl extends UnicastRemoteObject implements RMI_SaleService {
    private final SaleService saleService;

    public RMI_SaleServiceImpl(SaleService saleService) throws RemoteException {
        super(Integer.parseInt(System.getenv("JAZZERS_RMI_PORT")));
        this.saleService = saleService;
    }

    @Override
    public void purchase(UUID customerId, List<LineDTO> linesDTO) throws RemoteException {
        saleService.purchase(customerId, linesDTO);
    }

    @Override
    public void refund(UUID customerId, List<LineDTO> linesDTO) throws RemoteException {
        saleService.refund(customerId, linesDTO);
    }

    @Override
    public List<SaleHistoryEntryOverviewDTO> saleHistoryFull() throws RemoteException {
        return saleService.saleHistoryFull();
    }

    @Override
    public List<SaleHistoryEntryOverviewDTO> saleHistoryBy(String customerNameOrSaleId) throws RemoteException {
        return saleService.saleHistoryBy(customerNameOrSaleId);
    }

    @Override
    public SaleHistoryEntryDetailDTO saleDetail(UUID saleId) throws RemoteException {
        return saleService.saleDetail(saleId);
    }
}
