package br.com.tp.lanchescaieiras.customer.adapter.controllers;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class CustomerControllerImpl implements CustomerController {


    @Override
    public ResponseEntity<Response<Customer>> createCustomer(Customer customer) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseList<Customer>> getAllCustomers(Optional<Integer> _limit) {
        return null;
    }

    @Override
    public ResponseEntity<Response<Customer>> getCustomerById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Response<Customer>> getCustomerByDocumentNumber(String documentNumber) {
        return null;
    }

    @Override
    public ResponseEntity<Response<Customer>> partialUpdateCustomer(Customer customer, Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Response<Customer>> deleteCustomer(Integer id) {
        return null;
    }
}
