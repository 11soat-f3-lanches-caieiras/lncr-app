package br.com.tp.lanchescaieiras.customer.application.services.usecases;

import br.com.tp.lanchescaieiras.customer.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerUseCases {

    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(Integer id);
    List<Customer> getAllCustomers();
    void updateCustomer(Integer id, Customer customer);
    void deleteCustomer(Integer id);

}
