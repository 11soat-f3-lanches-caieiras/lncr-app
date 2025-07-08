package br.com.tp.lanchescaieiras._core.adapters.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.domain.customer.Customer;

import java.util.List;

public class CustomerPresenter {

    public final CustomerMapper customerMapper;

    public CustomerPresenter(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerDTO created(Customer customer) {
        return customerMapper.domainToDto(customer);
    }

    public List<CustomerDTO> getAll(List<Customer> customerList) {
        return customerList.stream().map(customerMapper::domainToDto).toList();
    }

    public CustomerDTO getbyId(Customer customer) {
        return customerMapper.domainToDto(customer);
    }

    public CustomerDTO getByDocumentNumber(Customer customer) {
        return customerMapper.domainToDto(customer);
    }

    public CustomerDTO partialUpdatedById(Customer customer) {
        return customerMapper.domainToDto(customer);
    }

    public List<CustomerDTO> getByIdList(List<Customer> customerList) {
        return customerList.stream().map(customerMapper::domainToDto).toList();
    }
}
