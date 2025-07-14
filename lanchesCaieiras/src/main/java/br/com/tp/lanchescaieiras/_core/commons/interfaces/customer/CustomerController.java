package br.com.tp.lanchescaieiras._core.commons.interfaces.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerController {

    CustomerDTO create(CustomerDTO customerDto);

    List<CustomerDTO> getAll(Optional<Integer> _limit);

    CustomerDTO getById(Integer id);

    CustomerDTO getByDocumentNumber(String documentNumber);

    CustomerDTO partialUpdateById(Integer id, CustomerDTO CustomerDTO);

    void delete(Integer id);

    List<CustomerDTO> getByIdList(List<Integer> customerIdList);
}
