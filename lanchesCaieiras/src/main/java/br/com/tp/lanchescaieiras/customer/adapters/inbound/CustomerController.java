package br.com.tp.lanchescaieiras.customer.adapters.inbound;

import br.com.tp.lanchescaieiras.customer.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;

public interface CustomerController {
    //public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer);

    //public ResponseEntity<List<Customer>> getAllCustomers(
    //        @RequestParam Optional<Integer> _limit);

    //public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id);

    //public ResponseEntity<Customer> getCustomerByDocumentNumber(@PathVariable String documentNumber);

    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer);

    //public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id);
}
