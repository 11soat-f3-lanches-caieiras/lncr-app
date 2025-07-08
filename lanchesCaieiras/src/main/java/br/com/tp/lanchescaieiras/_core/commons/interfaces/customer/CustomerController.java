package br.com.tp.lanchescaieiras._core.commons.interfaces.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresReposityImpl;

import java.util.List;
import java.util.Optional;

public interface CustomerController {

    CustomerDTO create(CustomerDTO customerDto, CustomerDatabase customerDatabase);

    List<CustomerDTO> getAll(Optional<Integer> _limit, CustomerDatabase customerDatabase);

    CustomerDTO getById(Integer id, CustomerDatabase customerDatabase);

    CustomerDTO getByDocumentNumber(String documentNumber, CustomerDatabase customerDatabase);

    CustomerDTO partialUpdateById(Integer id, CustomerDTO CustomerDTO, CustomerDatabase customerDatabase);

    void delete(Integer id, CustomerDatabase customerDatabase);

    List<CustomerDTO> getByIdList(List<Integer> customerIdList, JpaCustomerPostgresReposityImpl jpaCustomerPostgresReposity);
}
