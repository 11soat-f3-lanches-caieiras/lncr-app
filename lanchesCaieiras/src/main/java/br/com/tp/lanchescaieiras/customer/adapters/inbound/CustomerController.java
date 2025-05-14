package br.com.tp.lanchescaieiras.customer.adapters.inbound;

import br.com.tp.lanchescaieiras.customer.domain.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.CustomerResponse;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface CustomerController {

    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer);

    public ResponseEntity<CustomerListResponse> getAllCustomers(Optional<Integer> _limit);

    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer id);

    public ResponseEntity<CustomerResponse> getCustomerByDocumentNumber(@PathVariable String documentNumber);

    public ResponseEntity<CustomerResponse> partialUpdateCustomer(@RequestBody Customer customer, @PathVariable Integer id);

    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable Integer id);
}
