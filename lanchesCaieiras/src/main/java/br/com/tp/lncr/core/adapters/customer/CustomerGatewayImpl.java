package br.com.tp.lncr.core.adapters.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerDatabase;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.domain.customer.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerDatabase customerDatabase;
    private final CustomerMapper customerMapper;

    public CustomerGatewayImpl(CustomerDatabase customerDatabase, CustomerMapper customerMapper) {
        this.customerDatabase = customerDatabase;
        this.customerMapper = customerMapper;
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return this.customerDatabase.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.customerDatabase.existsByEmail(email);
    }

    @Override
    public List<Customer> getAllCustomers(Integer _limit) {
        return customerDatabase.findAll(_limit).stream()
                .map(customerMapper::customerToDTO)
                .toList();
    }

    @Override
    public Customer getCustomerByDocumentNumber(String documentNumber) {
        Optional<CustomerDTO> customerDTO = this.customerDatabase.findByDocumentNumber(documentNumber);
        if (customerDTO.isEmpty()) {
            return null;
        }
        return customerMapper.customerToDTO(customerDTO.get());
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<CustomerDTO> customerDTO = this.customerDatabase.findById(id);
        if (customerDTO.isEmpty()) {
            return null;
        }
        return customerMapper.customerToDTO(customerDTO.get());
    }

    @Override
    public List<Customer> getCustomerByIdList(List<Integer> customerIdList) {
        List<CustomerDTO> customerDTOList = this.customerDatabase.findByIdList(customerIdList);
        return customerDTOList.stream().map(customerMapper::customerToDTO).toList();
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = this.getCustomerById(id);
        if (customer == null) {
            throw new RuntimeException("Cliente não encontrado com o ID: " + id);
        }
        CustomerDTO customerDTO = customerMapper.customerToDomain(customer);
        this.customerDatabase.deleteById(id);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerDTO customerDTO = customerDatabase.save(customerMapper.customerToDomain(customer));
        return customerMapper.customerToDTO(customerDTO);
    }
}
