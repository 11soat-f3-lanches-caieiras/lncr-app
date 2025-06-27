package br.com.tp.lanchescaieiras.customer.application.usecases;

import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerGateway;
import br.com.tp.lanchescaieiras.customer.application.mappers.CustomerDtoMapper;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.exceptions.CustomerException;

public class CreateCustomerUseCase {

    private CustomerDtoMapper customerDtoMapper;

    public CreateCustomerUseCase() {
        this.customerDtoMapper = new CustomerDtoMapper();
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO,
                                      CustomerGateway customerGateway) {
        Customer customer = customerDtoMapper.dtoToDomain(customerDTO);
        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        if (getCustomerUseCase.existsByDocumentNumber(customerDTO, customerGateway)) {
            throw new CustomerException("Cliente já cadastrado com o mesmo número de documento: " + customerDTO.getDocumentNumber(), 409);
        }
        if (getCustomerUseCase.existsByEmail(customerDTO, customerGateway)) {
            throw new CustomerException("Cliente já cadastrado com o mesmo e-mail: " + customerDTO.getEmail(), 409);
        }
        return customerDtoMapper.domainToDto(customerGateway.save(customer));
    }
}
