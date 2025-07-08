package br.com.tp.lanchescaieiras._external.apis.customer;

import br.com.tp.lanchescaieiras._core.adapters.customer.CustomerControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseListModel;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import br.com.tp.lanchescaieiras._external.configs.CustomerConfig;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresReposityImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerRestControllerImpl implements CustomerRestController {

    public final CustomerControllerImpl customerController;
    public final JpaCustomerPostgresReposityImpl jpaCustomerPostgresReposity;
    public final CustomerConfig customerConfig;

    public CustomerRestControllerImpl(CustomerControllerImpl customerController,
                                      JpaCustomerPostgresReposityImpl jpaCustomerPostgresReposity,
                                      CustomerConfig customerConfig) {
        this.customerController = customerController;
        this.jpaCustomerPostgresReposity = jpaCustomerPostgresReposity;
        this.customerConfig = customerConfig;
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseModel<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDto) {
        customerDto = this.customerController.create(customerDto, this.jpaCustomerPostgresReposity);
        return ResponseEntityModelUtil.created(customerDto, customerConfig.getLocationPrefix());
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseListModel<CustomerDTO>> getAllCustomers(@RequestParam("_limit") Optional<Integer> _limit) {
        List<CustomerDTO> listCustomerDTO = this.customerController.getAll(_limit, this.jpaCustomerPostgresReposity);
        return ResponseEntityModelUtil.listOK(listCustomerDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<CustomerDTO>> getCustomerById(@PathVariable("id") Integer id) {
        CustomerDTO customerDTO = this.customerController.getById(id, this.jpaCustomerPostgresReposity);
        return ResponseEntityModelUtil.OK(customerDTO);
    }



    @Override
    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<ResponseModel<CustomerDTO>> getCustomerByDocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        CustomerDTO customerDTO = this.customerController.getByDocumentNumber(documentNumber, this.jpaCustomerPostgresReposity);
        return ResponseEntityModelUtil.OK(customerDTO);
    }

    @Override
    @GetMapping("/customerIdList/{customerIdList}")
    public ResponseEntity<ResponseListModel<CustomerDTO>> getCustomerByIdList(@PathVariable(name="customerIdList") List<Integer> customerIdList) {
        List<CustomerDTO> customerDTOList = this.customerController.getByIdList(customerIdList,this.jpaCustomerPostgresReposity);
        return ResponseEntityModelUtil.listOK(customerDTOList);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseModel<CustomerDTO>> partialUpdateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id) {
        customerDTO = this.customerController.partialUpdateById(id, customerDTO, this.jpaCustomerPostgresReposity);
        return ResponseEntityModelUtil.OK(customerDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<CustomerDTO>> deleteCustomer(@PathVariable("id") Integer id) {
        this.customerController.delete(id, this.jpaCustomerPostgresReposity);
        return ResponseEntityModelUtil.OK(null);
    }
}
