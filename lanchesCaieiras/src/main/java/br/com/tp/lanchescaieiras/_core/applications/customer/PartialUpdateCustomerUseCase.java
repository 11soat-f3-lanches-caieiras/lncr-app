package br.com.tp.lanchescaieiras._core.applications.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lanchescaieiras._core.domain.customer.Customer;

import java.lang.reflect.Field;

public class PartialUpdateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public PartialUpdateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer execute(Integer id, CustomerDTO customerDto) {
        Customer updatedCustomer = new Customer(customerDto);
        validateExistsCustomerByDocumentNumberAndEmail(updatedCustomer);
        Customer actualCustomer = getById(id);
        actualCustomer = mergeCustomerDto(actualCustomer, customerDto);
        updatedCustomer = customerGateway.save(actualCustomer);
        return updatedCustomer;
    }

    private Customer getById(Integer id) {
        Customer customer = customerGateway.findById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        return customer;
    }

    private void validateExistsCustomerByDocumentNumberAndEmail(Customer updatedCustomer) {
        existsByDocumentNumber(updatedCustomer);
        existsByEmail(updatedCustomer);
    }

    private void existsByDocumentNumber(Customer updatedCustomer) {
        if (customerGateway.existsByDocumentNumber(updatedCustomer.getDocumentNumber())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo número de documento: " + updatedCustomer.getDocumentNumber(), 409);
        }
    }

    private void existsByEmail(Customer updatedCustomer) {
        if (customerGateway.existsByEmail(updatedCustomer.getEmail())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo e-mail: " + updatedCustomer.getEmail(), 409);
        }
    }

    private Customer mergeCustomerDto(Customer actualCustomer, CustomerDTO updatedCustomerDto) {
        updatedCustomerDto.setId(null);

        try {
            for (Field field : Customer.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object newValue = field.get(updatedCustomerDto);
                if (newValue != null) {
                    field.set(actualCustomer, newValue);
                }
            }
        } catch (Exception e) {
            throw new CustomerException("Erro ao atualizar cliente: " + e.getMessage(), 500);
        }

        return actualCustomer;
    }

}
