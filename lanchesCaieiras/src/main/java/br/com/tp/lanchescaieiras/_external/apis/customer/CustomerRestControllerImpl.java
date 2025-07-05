package br.com.tp.lanchescaieiras._external.apis.customer;

import br.com.tp.lanchescaieiras._core.adapters.customer.CustomerControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityUtil;
import br.com.tp.lanchescaieiras._external.commons.model.Response;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseList;
import br.com.tp.lanchescaieiras._external.configs.CustomerConfig;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresReposityImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        customerDto = this.customerControllerImpl.create(customerDto, this.jpaCustomerPostgresReposityImpl);
        return ResponseEntityUtil.created(customerDto, customerConfig.getLocationPrefix());
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseList<CustomerDTO>> getAllCustomers(@RequestParam("_limit") Optional<Integer> _limit) {
        List<CustomerDTO> listCustomerDTO = this.customerControllerImpl.getAll(_limit, this.jpaCustomerPostgresReposityImpl);
        return ResponseEntityUtil.listOK(listCustomerDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Response<CustomerDTO>> getCustomerById(@PathVariable("id") Integer id) {
        CustomerDTO customerDTO = this.customerControllerImpl.getById(id, this.jpaCustomerPostgresReposityImpl);
        return ResponseEntityUtil.OK(customerDTO);
    }

    @Override
    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<Response<CustomerDTO>> getCustomerByDocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        CustomerDTO customerDTO = this.customerControllerImpl.getByDocumentNumber(documentNumber, this.jpaCustomerPostgresReposityImpl);
        return ResponseEntityUtil.OK(customerDTO);

    }


    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Response<CustomerDTO>> partialUpdateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id) {
        customerDTO = this.customerControllerImpl.partialUpdateById(id, customerDTO, this.jpaCustomerPostgresReposityImpl);
        return ResponseEntityUtil.OK(customerDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<CustomerDTO>> deleteCustomer(@PathVariable("id") Integer id) {
        this.customerControllerImpl.delete(id, this.jpaCustomerPostgresReposityImpl);
        return ResponseEntityUtil.OK(null);
    }
}
