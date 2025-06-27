package br.com.tp.lanchescaieiras.customer.adapters;

import br.com.tp.lanchescaieiras.customer.application.mappers.CustomerDtoMapper;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerGateway;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerDatabase;

import java.util.List;
import java.util.Optional;

public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerDatabase customerDatabase;
    private final CustomerDtoMapper customerDtoMapper = new CustomerDtoMapper();

    public CustomerGatewayImpl(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerDTO customerDTO = customerDatabase.save(customerDtoMapper.domainToDto(customer));
        return customerDtoMapper.dtoToDomain(customerDTO);
    }

    @Override
    public Customer findById(Integer id) {
        Optional<CustomerDTO> customerDTO = this.customerDatabase.findById(id);
        if (customerDTO.isEmpty()) {
            return null;
        }
        return customerDtoMapper.dtoToDomain(customerDTO.get());
    }

    @Override
    public Customer findByDocumentNumber(String documentNumber) {
        Optional<CustomerDTO> customerDTO = this.customerDatabase.findByDocumentNumber(documentNumber);
        if (customerDTO.isEmpty()) {
            return null;
        }
        return customerDtoMapper.dtoToDomain(customerDTO.get());
    }

    @Override
    public List<Customer> findAll(Integer _limit) {
        return customerDatabase.findAll(_limit).stream()
                .map(customerDtoMapper::dtoToDomain)
                .toList();
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return this.customerDatabase.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.customerDatabase.existsByEmail(email);
    }

    public void deleteById(Integer id) {
        Customer customer = this.findById(id);
        if (customer == null) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + id);
        }
        CustomerDTO customerDTO = customerDtoMapper.domainToDto(customer);
        this.customerDatabase.deleteById(id);
    }
}
