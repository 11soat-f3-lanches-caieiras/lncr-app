package br.com.tp.lanchescaieiras.customer.application;

import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lanchescaieiras.customer.adapters.CustomerMapper;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.domain.exceptions.CustomerException;

public class CreateCustomerUseCase {

    public CreateCustomerUseCase() {
    }

    public CustomerDTO execute(CustomerDTO customerDTO,
                                      CustomerGateway customerGateway,
                                      CustomerMapper customerMapper) {
        Customer customer = customerMapper.dtoToDomain(customerDTO);
        validateUpdateFields(customer, customerGateway);
        return customerMapper.domainToDto(customerGateway.save(customer));
    }

    private void validateUpdateFields(Customer customer, CustomerGateway customerGateway) {
        existsByDocumentNumber(customer, customerGateway);
        existsByEmail(customer, customerGateway);
    }

    private void existsByDocumentNumber(Customer customer, CustomerGateway customerGateway) {
        if (customerGateway.existsByDocumentNumber(customer.getDocumentNumber())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo número de documento: " + customer.getDocumentNumber(), 409);
        }
    }

    private void existsByEmail(Customer customer, CustomerGateway customerGateway) {
        if (customerGateway.existsByEmail(customer.getEmail())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo e-mail: " + customer.getEmail(), 409);
        }
    }
}
