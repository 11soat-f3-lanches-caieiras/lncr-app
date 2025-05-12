package br.com.tp.lanchescaieiras.customer.adapters.inbound;

import br.com.tp.lanchescaieiras.customer.application.services.services.CustomerServiceImpl;
import br.com.tp.lanchescaieiras.customer.application.services.usecases.CustomerUseCases;
import br.com.tp.lanchescaieiras.customer.domain.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerController {

    public final CustomerServiceImpl customerService;

    public CustomerControllerImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = this.customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }



}
