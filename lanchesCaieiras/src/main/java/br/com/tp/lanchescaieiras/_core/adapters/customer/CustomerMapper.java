package br.com.tp.lanchescaieiras._core.adapters.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.domain.customer.Customer;

public class CustomerMapper {

    public CustomerDTO customerToDomain(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getDocumentNumber(),
                customer.getName(),
                customer.getEmail()
        );
    }

    public Customer customerToDTO(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getId(),
                customerDTO.getDocumentNumber(),
                customerDTO.getName(),
                customerDTO.getEmail()
        );
    }
}








