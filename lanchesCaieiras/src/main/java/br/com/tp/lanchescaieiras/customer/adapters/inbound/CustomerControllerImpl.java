package br.com.tp.lanchescaieiras.customer.adapters.inbound;

import br.com.tp.lanchescaieiras.customer.application.services.CustomerServiceImpl;
import br.com.tp.lanchescaieiras.customer.domain.CustomerListResponse;
import br.com.tp.lanchescaieiras.customer.domain.CustomerResponse;
import br.com.tp.lanchescaieiras.customer.domain.Customer;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerController {

    public final CustomerServiceImpl customerService;

    public CustomerControllerImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        Customer responseCustomer = this.customerService.createCustomer(customer);
        responseCustomer.setName(null);
        responseCustomer.setEmail(null);
        responseCustomer.setDocumentNumber(null);
        CustomerResponse createdCustomer = new CustomerResponse(
                new ResponseMetada(UUID.randomUUID().toString(), OffsetDateTime.now().toString()),
                responseCustomer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<CustomerListResponse> getAllCustomers(Optional<Integer> _limit) {
        if (_limit.isPresent() && (_limit.get() <= 0 || _limit.get() > 50)) {
            throw new IllegalArgumentException("Limite deve ser maior que 0 e menor ou igual a 50");
        }
        List<Customer> customerList = this.customerService.getAllCustomers(_limit.orElse(10));
        CustomerListResponse response = new CustomerListResponse(
            new ResponseMetada(UUID.randomUUID().toString(), OffsetDateTime.now().toString()),
            customerList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Integer id) {
        Optional<Customer> customer = this.customerService.getCustomerById(id);
        return getCustomerResponseEntity(customer);
    }

    @Override
    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<CustomerResponse> getCustomerByDocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        Optional<Customer> customer = this.customerService.getCustomerByDocumentNumber(documentNumber);
        return getCustomerResponseEntity(customer);
    }


    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> partialUpdateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
        Customer updatedCustomer = this.customerService.partialUpdateCustomer(customer, id);
        return getCustomerResponseEntity(updatedCustomer);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable("id") Integer id) {
        Boolean deleted = this.customerService.deleteCustomer(id);
        if (deleted) {
            return new ResponseEntity<>(new CustomerResponse(new ResponseMetada(UUID.randomUUID().toString(), OffsetDateTime.now().toString()), null), HttpStatus.OK);
        } else {
            throw new CustomerException("Cliente não encontrado", 404);
        }
    }

    private ResponseEntity<CustomerResponse> getCustomerResponseEntity(Customer customer){
        return getCustomerResponseEntity(Optional.of(customer));
    }

    private ResponseEntity<CustomerResponse> getCustomerResponseEntity(Optional<Customer> customer) {
        if (customer.isPresent()) {
            CustomerResponse getCustomer = new CustomerResponse(new ResponseMetada(),customer.orElse(null));
            getCustomer.get_response().set_traceId(UUID.randomUUID().toString());
            getCustomer.get_response().set_timestamp(OffsetDateTime.now().toString());
            return new ResponseEntity<>(getCustomer, HttpStatus.OK);
        } else{
            throw new CustomerException("Cliente não encontrado", 404);
        }
    }




}
