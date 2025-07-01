package br.com.tp.lanchescaieiras.customer.external.api;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface CustomerRestController {

    ResponseEntity<Response<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDto);

    ResponseEntity<ResponseList<CustomerDTO>> getAllCustomers(Optional<Integer> _limit);

    ResponseEntity<Response<CustomerDTO>> getCustomerById(@PathVariable Integer id);

    ResponseEntity<Response<CustomerDTO>> getCustomerByDocumentNumber(@PathVariable String documentNumber);

    ResponseEntity<Response<CustomerDTO>> partialUpdateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id);

    ResponseEntity<Response<CustomerDTO>> deleteCustomer(@PathVariable Integer id);
}
