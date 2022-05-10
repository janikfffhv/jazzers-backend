package at.fhv.jazzers.backend.communication;

import at.fhv.jazzers.backend.application.api.SaleService;
import at.fhv.jazzers.shared.api.RemoteSaleService;
import at.fhv.jazzers.shared.dto.LineDTO;
import at.fhv.jazzers.shared.dto.SaleHistoryEntryDetailDTO;
import at.fhv.jazzers.shared.dto.SaleHistoryEntryOverviewDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

@Stateless
public class RemoteSaleServiceImpl implements RemoteSaleService {
    @EJB
    private SaleService saleService;

    public RemoteSaleServiceImpl() {

    }

    @Override
    public void purchase(UUID customerId, List<LineDTO> linesDTO) {
        saleService.purchase(customerId, linesDTO);
    }

    @Override
    public void refund(UUID customerId, List<LineDTO> linesDTO) {
        saleService.refund(customerId, linesDTO);
    }

    @Override
    public List<SaleHistoryEntryOverviewDTO> saleHistoryFull() {
        return saleService.saleHistoryFull();
    }

    @Override
    public List<SaleHistoryEntryOverviewDTO> saleHistoryBy(String customerNameOrSaleId) {
        return saleService.saleHistoryBy(customerNameOrSaleId);
    }

    @Override
    public SaleHistoryEntryDetailDTO saleDetail(UUID saleId) {
        return saleService.saleDetail(saleId);
    }
}
