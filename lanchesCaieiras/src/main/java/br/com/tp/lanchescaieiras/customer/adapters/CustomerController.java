package br.com.tp.lanchescaieiras.customer.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerDatabase;
import br.com.tp.lanchescaieiras.customer.external.config.CustomerConfig;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CustomerController {

    public ResponseEntity<Response<CustomerDTO>> create(CustomerDTO customerDto, CustomerDatabase customerDatabase, CustomerConfig config);

    public ResponseEntity<ResponseList<CustomerDTO>> getAll(Optional<Integer> _limit, CustomerDatabase customerDatabase);

    public ResponseEntity<Response<CustomerDTO>> getById(Integer id, CustomerDatabase customerDatabase);

    public ResponseEntity<Response<CustomerDTO>> getByDocumentNumber(String documentNumber, CustomerDatabase customerDatabase);
    public ResponseEntity<Response<CustomerDTO>> partialUpdateById(Integer id, CustomerDTO CustomerDTO,CustomerDatabase customerDatabase);
    public ResponseEntity<Response<CustomerDTO>> delete(Integer id, CustomerDatabase customerDatabase);
}
