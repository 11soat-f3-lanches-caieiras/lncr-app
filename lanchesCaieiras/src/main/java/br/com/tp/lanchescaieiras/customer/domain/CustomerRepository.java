package br.com.tp.lanchescaieiras.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    List<Customer> findAll(Integer _limit);

    Optional<Customer> findById(Integer id);

    Optional<Customer> findByDocumentNumber(String documentNumber);

    Optional<Customer> partialUpdateById(Customer customer, Integer id);

    void deleteById(Integer id);

    Boolean existsByDocumentNumber(String documentNumber);

    Boolean existsByEmail(String email);


}