package br.com.tp.lanchescaieiras.customer.application.services;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.repositories.JpaCustomerReposityImpl;
import br.com.tp.lanchescaieiras.customer.application.usecases.CustomerUseCases;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerUseCases {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final JpaCustomerReposityImpl customerRepository;

    public CustomerServiceImpl(JpaCustomerReposityImpl customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        log.info("Iniciando criação de cliente: {}", customer.toString());
        validateDocumentNumber(customer);
        validateEmail(customer);
        customer = this.customerRepository.save(customer);
        log.info("Cliente criado com sucesso: {}", customer.toString());
        return customer;
    }


    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        log.info("Buscando cliente com id: " + id);
        Optional<Customer> customer = this.customerRepository.findById(id);

        if (customer.isPresent()) {
            log.info("Cliente encontrado: " + customer.get());
            return customer;
        } else {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
    }

    @Override
    public List<Customer> getAllCustomers(Integer limit) {
        log.info("Buscando {} os clientes", limit);
        return this.customerRepository.findAll(limit);
    }

    @Override
    public Optional<Customer> getCustomerByDocumentNumber(String documentNumber) {
        log.info("Buscando cliente pelo documento: {}", documentNumber);
        return this.customerRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Customer partialUpdateCustomer(Customer customer, Integer id) {
        log.info("Atualizando informações do cliente");
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
        log.warn("Deletando cliente com id: " + id);
        this.customerRepository.deleteById(id);
    }

    public void validateDocumentNumber(Customer customer) {
        log.info("Validando documento: " + customer.getDocumentNumber());
        if (customer.documentNumberIsValid()) {
            if (customerRepository.existsByDocumentNumber(customer.getDocumentNumber())) {
                throw new CustomerException("Documento já utilizado por outro cliente", 409);
            }
        } else {
            throw new CustomerException("Documento informado invalido", 404);
        }
    }

    public void validateEmail(Customer customer) {
        log.info("Validando email com o ID: " + customer.getEmail());
        if (customer.emailIsValid()) {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new CustomerException("Email já utilizado por outro cliente", 409);
            }
        } else {
            throw new CustomerException("Email informado invalido", 404);
        }
    }
}