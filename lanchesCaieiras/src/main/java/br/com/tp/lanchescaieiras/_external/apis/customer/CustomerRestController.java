package br.com.tp.lanchescaieiras._external.apis.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._external.commons.model.Response;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseList;
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
