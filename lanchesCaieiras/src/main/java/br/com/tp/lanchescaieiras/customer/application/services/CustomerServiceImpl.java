package br.com.tp.lanchescaieiras.customer.application.services;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories.JpaCustomerReposityImpl;
import br.com.tp.lanchescaieiras.customer.application.usecases.CustomerUseCases;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerUseCases {

    private final JpaCustomerReposityImpl customerRepository;

    public CustomerServiceImpl(JpaCustomerReposityImpl customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        validateDocumentNumber(customer);
        validateEmail(customer);
        customer = this.customerRepository.save(customer);
        return customer;
    }


    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer;
        } else {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
    }

    @Override
    public List<Customer> getAllCustomers(Integer limit) {
          return this.customerRepository.findAll(limit);
    }

    @Override
    public Optional<Customer> getCustomerByDocumentNumber(String documentNumber) {
            return this.customerRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Customer partialUpdateCustomer(Customer customer, Integer id) {
        if (customer.getDocumentNumber() != null) {
            validateDocumentNumber(customer);
        }
        if (customer.getEmail() != null) {
            validateEmail(customer);
        }
        return this.customerRepository.partialUpdateById(customer, id)
                .orElseThrow(() -> new CustomerException("Cliente não encontrado com o ID: " + id, 404));

    }

    @Override
    public void deleteCustomer(Integer id) {
        this.customerRepository.deleteById(id);
    }

    public void validateDocumentNumber(Customer customer) {
        if (customer.documentNumberIsValid()) {
            if (customerRepository.existsByDocumentNumber(customer.getDocumentNumber())) {
                throw new CustomerException("Documento já utilizado por outro cliente", 409);
            }
        } else {
            throw new CustomerException("Documento informado invalido", 404);
        }
    }

    public void validateEmail(Customer customer) {
        if (customer.emailIsValid()) {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new CustomerException("Email já utilizado por outro cliente", 409);
            }
        } else {
            throw new CustomerException("Email informado invalido", 404);
        }
    }
}