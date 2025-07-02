package br.com.tp.lanchescaieiras.customer.application;

import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lanchescaieiras.customer.adapters.CustomerMapper;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import br.com.tp.lanchescaieiras.customer.domain.exceptions.CustomerException;

import java.lang.reflect.Field;

public class PartialUpdateCustomerUseCase {

    public CustomerDTO execute(Integer id, CustomerDTO customerDto, CustomerGateway customerGateway, CustomerMapper customerMapper) {
       validateUpdateFields(customerDto, customerGateway);
       CustomerDTO existingCustomerDto = getById(id, customerGateway, customerMapper);
       Customer updatedCustomer = mergeCustomerDto(existingCustomerDto, customerDto, customerMapper);
       updatedCustomer = customerGateway.save(updatedCustomer);
       return customerMapper.domainToDto(updatedCustomer);
    }

    private CustomerDTO getById(Integer id, CustomerGateway customerGateway, CustomerMapper customerMapper) {
        Customer customer = customerGateway.findById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        return customerMapper.domainToDto(customer);
    }

    private void validateUpdateFields(CustomerDTO updateCustomerDto, CustomerGateway customerGateway) {
        existsByDocumentNumber(updateCustomerDto, customerGateway);
        existsByEmail(updateCustomerDto, customerGateway);
    }

    private void existsByDocumentNumber(CustomerDTO customerDto, CustomerGateway customerGateway) {
        if (customerGateway.existsByDocumentNumber(customerDto.getDocumentNumber())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo número de documento: " + customerDto.getDocumentNumber(), 409);
        }
    }

    private void existsByEmail(CustomerDTO customerDto, CustomerGateway customerGateway) {
        if (customerGateway.existsByEmail(customerDto.getEmail())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo e-mail: " + customerDto.getEmail(), 409);
        }
    }

    private Customer mergeCustomerDto(CustomerDTO existingCustomerDto, CustomerDTO updatedCustomerDto, CustomerMapper customerMapper) {
        Customer existingCustomer = customerMapper.dtoToDomain(existingCustomerDto);
        Customer updatedCustomer = customerMapper.dtoToDomain(updatedCustomerDto);
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
