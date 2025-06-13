package br.com.tp.lanchescaieiras.customer.application.usecases.interfaces;

import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerUseCases {

    Customer createCustomer(Customer customer);

    Optional<Customer> getCustomerById(Integer id);

    List<Customer> getAllCustomers(Integer limit);

    Optional<Customer> getCustomerByDocumentNumber(String documentNumber);

    Customer partialUpdateCustomer(Customer customer, Integer id);

    void deleteCustomer(Integer id);

}
