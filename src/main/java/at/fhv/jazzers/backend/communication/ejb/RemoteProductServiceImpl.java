package at.fhv.jazzers.backend.communication.ejb;

import at.fhv.jazzers.backend.application.api.ProductService;
import at.fhv.jazzers.shared.api.RemoteProductService;
import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RemoteProductServiceImpl implements RemoteProductService {
    @EJB
    private ProductService productService;

    public RemoteProductServiceImpl() {

    }

    @Override
    public List<ProductOverviewDTO> searchAnalog(String titleOrInterpret) {
        return productService.searchAnalog(titleOrInterpret);
    }

    @Override
    public List<DigitalProductDTO> searchDigital(String titleOrInterpret) {
        return productService.searchDigital(titleOrInterpret);
    }
}
