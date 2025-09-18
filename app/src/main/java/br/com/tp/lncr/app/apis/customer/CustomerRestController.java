package br.com.tp.lncr.app.apis.customer;

import br.com.tp.lncr.app.commons.model.ResponseListModel;
import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface CustomerRestController {

    ResponseEntity<ResponseModel<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDto);

    ResponseEntity<ResponseListModel<CustomerDTO>> getAllCustomers(Optional<Integer> _limit);

    ResponseEntity<ResponseModel<CustomerDTO>> getCustomerById(@PathVariable Integer id);

    ResponseEntity<ResponseListModel<CustomerDTO>> getCustomerByIdList(@PathVariable List<Integer> customerIdList);

    ResponseEntity<ResponseModel<CustomerDTO>> getCustomerByDocumentNumber(@PathVariable String documentNumber);

    ResponseEntity<ResponseModel<CustomerDTO>> partialUpdateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Integer id);

    ResponseEntity<ResponseModel<CustomerDTO>> deleteCustomer(@PathVariable Integer id);
}
