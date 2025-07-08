package br.com.tp.lanchescaieiras._core.commons.interfaces.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerDatabase {

    CustomerDTO save(CustomerDTO customerDto);

    Optional<CustomerDTO> findById(Integer id);

    List<CustomerDTO> findAll(Integer _limit);

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);

    Optional<CustomerDTO> findByDocumentNumber(String documentNumber);

    void deleteById(Integer id);

    List<CustomerDTO> findByIdList(List<Integer> customerIdList);
}
