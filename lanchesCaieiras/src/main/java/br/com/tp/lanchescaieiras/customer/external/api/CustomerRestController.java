package br.com.tp.lanchescaieiras.customer.external.api;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface CustomerRestController {

    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer customer);

    public ResponseEntity<ResponseList<Customer>> getAllCustomers(Optional<Integer> _limit);

    public ResponseEntity<Response<Customer>> getCustomerById(@PathVariable Integer id);

    public ResponseEntity<Response<Customer>> getCustomerByDocumentNumber(@PathVariable String documentNumber);

    public ResponseEntity<Response<Customer>> partialUpdateCustomer(@RequestBody Customer customer, @PathVariable Integer id);

    public ResponseEntity<Response<Customer>> deleteCustomer(@PathVariable Integer id);
}
