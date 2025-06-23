package br.com.tp.lanchescaieiras.customer.adapter.controllers;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface CustomerController {

    public ResponseEntity<Response<Customer>> createCustomer(Customer customer);

    public ResponseEntity<ResponseList<Customer>> getAllCustomers(Optional<Integer> _limit);

    public ResponseEntity<Response<Customer>> getCustomerById(Integer id);

    public ResponseEntity<Response<Customer>> getCustomerByDocumentNumber(String documentNumber);

    public ResponseEntity<Response<Customer>> partialUpdateCustomer(Customer customer, Integer id);

    public ResponseEntity<Response<Customer>> deleteCustomer(Integer id);
}
