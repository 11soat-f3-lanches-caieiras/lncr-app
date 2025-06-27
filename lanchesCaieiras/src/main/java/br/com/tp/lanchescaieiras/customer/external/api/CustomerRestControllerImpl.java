package br.com.tp.lanchescaieiras.customer.external.api;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.customer.adapters.CustomerControllerImpl;
import br.com.tp.lanchescaieiras.customer.external.config.CustomerConfig;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.customer.external.datasources.postgres.JpaCustomerPostgresReposityImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerRestControllerImpl implements CustomerRestController {

    public final CustomerControllerImpl customerControllerImpl;
    public final JpaCustomerPostgresReposityImpl jpaCustomerPostgresReposityImpl;
    public final CustomerConfig customerConfig;

    public CustomerRestControllerImpl(CustomerControllerImpl customerControllerImpl,
                                      JpaCustomerPostgresReposityImpl jpaCustomerPostgresReposityImpl,
                                      CustomerConfig customerConfig) {
        this.customerControllerImpl = customerControllerImpl;
        this.jpaCustomerPostgresReposityImpl = jpaCustomerPostgresReposityImpl;
        this.customerConfig = customerConfig;
    }

    @Override
    @PostMapping
    public ResponseEntity<Response<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDto) {
        return this.customerControllerImpl.create(customerDto, this.jpaCustomerPostgresReposityImpl, this.customerConfig);
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseList<CustomerDTO>> getAllCustomers(Optional<Integer> _limit) {
        return this.customerControllerImpl.getAll(_limit, this.jpaCustomerPostgresReposityImpl);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Response<CustomerDTO>> getCustomerById(@PathVariable("id") Integer id) {
        return this.customerControllerImpl.getById(id, this.jpaCustomerPostgresReposityImpl);
    }

    @Override
    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<Response<CustomerDTO>> getCustomerByDocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        return this.customerControllerImpl.getByDocumentNumber(documentNumber,this.jpaCustomerPostgresReposityImpl);
    }


    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Response<CustomerDTO>> partialUpdateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id) {
        return this.customerControllerImpl.partialUpdateById(id, customerDTO, this.jpaCustomerPostgresReposityImpl);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<CustomerDTO>> deleteCustomer(@PathVariable("id") Integer id) {
        return this.customerControllerImpl.delete(id, this.jpaCustomerPostgresReposityImpl);
    }
}
