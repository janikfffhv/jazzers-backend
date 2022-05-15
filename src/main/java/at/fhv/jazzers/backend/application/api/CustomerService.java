package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.ProductOverviewDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerService {
    List<ProductOverviewDTO> collection(String username);
}
