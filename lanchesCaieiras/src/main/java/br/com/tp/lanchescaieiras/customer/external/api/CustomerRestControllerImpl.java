package br.com.tp.lanchescaieiras.customer.external.api;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.customer.adapter.controllers.CustomerControllerImpl;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.shared.CustomerResponse;
import br.com.tp.lanchescaieiras.customer.domain.shared.exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerRestControllerImpl implements CustomerRestController {

    public final CustomerControllerImpl customerControllerImpl;

    public CustomerRestControllerImpl(CustomerControllerImpl customerControllerImpl) {
        this.customerControllerImpl = customerControllerImpl;
    }

    @Override
    @PostMapping
    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer customer) {
        return this.customerControllerImpl.createCustomer(customer);
        /*return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", customerConfig.getLocationPrefix() + "/" + customer.getId())
                .body(new CustomerResponse());*/
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseList<Customer>> getAllCustomers(Optional<Integer> _limit) {
        /*if (_limit.isPresent() && (_limit.get() <= 0 || _limit.get() > 50)) {
            throw new CustomerException("Limite deve ser maior que 0 e menor ou igual a 50",400);
        }
        List<Customer> customerList = this.customerService.getAllCustomers(_limit.orElse(10));
        return new ResponseEntity<>(new CustomerListResponse(customerList), HttpStatus.OK);*/
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Response<Customer>> getCustomerById(@PathVariable("id") Integer id) {
        /*Optional<Customer> customer = this.customerService.getCustomerById(id);
        return new ResponseEntity<>(new CustomerResponse(customer.get()), HttpStatus.OK);*/
        return null;
    }

    @Override
    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<Response<Customer>> getCustomerByDocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        /*Optional<Customer> customer = this.customerService.getCustomerByDocumentNumber(documentNumber);
        return new ResponseEntity<>(new CustomerResponse(customer.get()), HttpStatus.OK);*/
        return null;
    }


    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Customer>> partialUpdateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        /*Customer updatedCustomer = this.customerService.partialUpdateCustomer(customer, id);
        return new ResponseEntity<>(new CustomerResponse(updatedCustomer), HttpStatus.OK);*/
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Customer>> deleteCustomer(@PathVariable("id") Integer id) {
        /*this.customerService.deleteCustomer(id);
        return new ResponseEntity<>(new CustomerResponse(null), HttpStatus.OK);*/
        return null;
    }
}
