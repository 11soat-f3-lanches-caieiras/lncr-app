package br.com.tp.lncr.core.commons.interfaces.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerController {

    CustomerDTO create(CustomerDTO customerDto);

    void delete(Integer id);

    List<CustomerDTO> getAll(Optional<Integer> _limit);

    List<CustomerDTO> getByIdList(List<Integer> customerIdList);

    CustomerDTO getByDocumentNumber(String documentNumber);

    CustomerDTO getById(Integer id);

    CustomerDTO partialUpdateById(Integer id, CustomerDTO CustomerDTO);
}
