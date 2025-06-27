package br.com.tp.lanchescaieiras.commons.interfaces;

import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;

import java.util.List;

public interface CustomerGateway<T> {

    Customer save(Customer customer);
    Customer findById(Integer id);
    Customer findByDocumentNumber(String documentNumber);
    List<Customer> findAll(Integer _limit);
/*


    Optional<Customer> partialUpdateById(Customer customer, Integer id);

    void deleteById(Integer id);
*/

    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);


}