package br.com.tp.lncr.core.commons.interfaces.customer;

import br.com.tp.lncr.core.domain.customer.Customer;

import java.util.List;

public interface CustomerGateway {

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);

    void deleteCustomerById(Integer id);

    List<Customer> getAllCustomers(Integer _limit);

    Customer getCustomerByDocumentNumber(String documentNumber);

    Customer getCustomerById(Integer id);

    List<Customer> getCustomerByIdList(List<Integer> customerIdList);

    Customer save(Customer customer);
}