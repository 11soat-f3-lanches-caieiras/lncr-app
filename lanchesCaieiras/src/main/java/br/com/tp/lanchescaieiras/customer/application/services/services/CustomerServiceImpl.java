package br.com.tp.lanchescaieiras.customer.application.services.services;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories.JpaCustomerReposityImpl;
import br.com.tp.lanchescaieiras.customer.application.services.usecases.CustomerUseCases;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CustomerServiceImpl implements CustomerUseCases {

    private final JpaCustomerReposityImpl customerRepository;
    private final CustomerMapper customerMapper;
    public CustomerServiceImpl(JpaCustomerReposityImpl customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public Customer createCustomer(Customer customer) {
       return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }

    @Override
    public void updateCustomer(Integer id, Customer customer) {

    }

    @Override
    public void deleteCustomer(Integer id) {

    }



}
