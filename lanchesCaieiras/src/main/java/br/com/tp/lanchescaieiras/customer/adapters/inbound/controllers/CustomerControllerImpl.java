package br.com.tp.lanchescaieiras.customer.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.customer.application.services.CustomerServiceImpl;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.domain.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.CustomerResponse;
import br.com.tp.lanchescaieiras.customer.infraestructure.config.CustomerConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerController {

    public final CustomerConfig customerConfig;
    public final CustomerServiceImpl customerService;

    public CustomerControllerImpl(CustomerServiceImpl customerService, CustomerConfig customerConfig) {
        this.customerService = customerService;
        this.customerConfig = customerConfig;
    }

    @Override
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        customer = this.customerService.createCustomer(customer);
        Customer createdCustomer = new Customer();
        createdCustomer.setId(customer.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", customerConfig.getLocationPrefix() + "/" + customer.getId())
                .body(new CustomerResponse(createdCustomer));
    }

    @Override
    @GetMapping
    public ResponseEntity<CustomerListResponse> getAllCustomers(Optional<Integer> _limit) {
        if (_limit.isPresent() && (_limit.get() <= 0 || _limit.get() > 50)) {
            throw new IllegalArgumentException("Limite deve ser maior que 0 e menor ou igual a 50");
        }
        List<Customer> customerList = this.customerService.getAllCustomers(_limit.orElse(10));
        return new ResponseEntity<>(new CustomerListResponse(customerList), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Integer id) {
        Optional<Customer> customer = this.customerService.getCustomerById(id);
        return new ResponseEntity<>(new CustomerResponse(customer.get()), HttpStatus.OK);
    }

    @Override
    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<CustomerResponse> getCustomerByDocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        Optional<Customer> customer = this.customerService.getCustomerByDocumentNumber(documentNumber);
        return new ResponseEntity<>(new CustomerResponse(customer.get()), HttpStatus.OK);
    }


    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> partialUpdateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        Customer updatedCustomer = this.customerService.partialUpdateCustomer(customer, id);
        return new ResponseEntity<>(new CustomerResponse(updatedCustomer), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable("id") Integer id) {
        this.customerService.deleteCustomer(id);
        return new ResponseEntity<>(new CustomerResponse(null), HttpStatus.OK);
    }
}
