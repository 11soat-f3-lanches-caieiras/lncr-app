package br.com.tp.lanchescaieiras.customer.application.usecases;

import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerGateway;
import br.com.tp.lanchescaieiras.customer.application.mappers.CustomerDtoMapper;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.exceptions.CustomerException;

import java.lang.reflect.Field;

public class PartialUpdateCustomerUseCase {

    private CustomerDtoMapper customerDtoMapper;

    public PartialUpdateCustomerUseCase() {
        this.customerDtoMapper = new CustomerDtoMapper();
    }

    public CustomerDTO partialUpdateById(Integer id, CustomerDTO customerDto, CustomerGateway customerGateway) {
       validateUpdateFields(customerDto, customerGateway);
       CustomerDTO existingCustomerDto = getById(id, customerGateway);
       Customer updatedCustomer = mergeCustomerDto(existingCustomerDto, customerDto);
       updatedCustomer = customerGateway.save(updatedCustomer);
       return customerDtoMapper.domainToDto(updatedCustomer);
    }

    private CustomerDTO getById(Integer id, CustomerGateway customerGateway) {
        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        return getCustomerUseCase.getById(id, customerGateway);
    }

    private boolean validateUpdateFields(CustomerDTO updateCustomerDto, CustomerGateway customerGateway) {
        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        if (getCustomerUseCase.existsByDocumentNumber(updateCustomerDto, customerGateway)) {
            throw new CustomerException("Cliente já cadastrado com o mesmo número de documento: " + updateCustomerDto.getDocumentNumber(), 409);
        }
        if (getCustomerUseCase.existsByEmail(updateCustomerDto, customerGateway)) {
            throw new CustomerException("Cliente já cadastrado com o mesmo e-mail: " + updateCustomerDto.getEmail(), 409);
        }
        return true;
    }

    private Customer mergeCustomerDto(CustomerDTO existingCustomerDto, CustomerDTO updatedCustomerDto) {
        Customer existingCustomer = customerDtoMapper.dtoToDomain(existingCustomerDto);
        Customer updatedCustomer = customerDtoMapper.dtoToDomain(updatedCustomerDto);
        updatedCustomer.setId(null);

        try {
            for (Field field : Customer.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object newValue = field.get(updatedCustomer);
                if (newValue != null) {
                    field.set(existingCustomer, newValue);
                }
            }
        }catch (Exception e){
            throw new CustomerException("Erro ao atualizar cliente: " + e.getMessage(), 500);
        }

        return existingCustomer;
    }

}
