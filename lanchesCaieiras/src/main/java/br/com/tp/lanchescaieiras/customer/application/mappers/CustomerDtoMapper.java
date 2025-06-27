package br.com.tp.lanchescaieiras.customer.application.mappers;

import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;

public class CustomerDtoMapper {

    public CustomerDTO domainToDto(Customer customer) {
        return new CustomerDTO(
            customer.getId(),
            customer.getDocumentNumber(),
            customer.getName(),
            customer.getEmail()
        );
    }

    public Customer dtoToDomain(CustomerDTO customerDTO) {
        return new Customer(
            customerDTO.getId(),
            customerDTO.getDocumentNumber(),
            customerDTO.getName(),
            customerDTO.getEmail()
        );
    }
}








