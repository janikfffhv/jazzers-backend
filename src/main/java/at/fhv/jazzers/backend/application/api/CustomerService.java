package at.fhv.jazzers.backend.application.api;

import at.fhv.jazzers.shared.dto.CustomerAccountDTO;
import at.fhv.jazzers.shared.dto.DigitalProductDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerService {
    CustomerAccountDTO accountInfo(String username);
    List<DigitalProductDTO> collection(String username);
}
