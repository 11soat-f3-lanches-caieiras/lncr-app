package br.com.tp.lanchescaieiras.customer.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerDatabase;
import br.com.tp.lanchescaieiras.customer.external.config.CustomerConfig;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerController {

    CustomerDTO create(CustomerDTO customerDto, CustomerDatabase customerDatabase);

    List<CustomerDTO> getAll(Optional<Integer> _limit, CustomerDatabase customerDatabase);

    CustomerDTO getById(Integer id, CustomerDatabase customerDatabase);

    CustomerDTO getByDocumentNumber(String documentNumber, CustomerDatabase customerDatabase);
    CustomerDTO partialUpdateById(Integer id, CustomerDTO CustomerDTO, CustomerDatabase customerDatabase);
    void delete(Integer id, CustomerDatabase customerDatabase);
}
