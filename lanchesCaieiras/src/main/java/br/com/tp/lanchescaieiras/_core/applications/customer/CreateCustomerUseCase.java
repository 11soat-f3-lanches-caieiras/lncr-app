package br.com.tp.lanchescaieiras._core.applications.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lanchescaieiras._core.domain.customer.Customer;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerException;

public class CreateCustomerUseCase {

    public final CustomerGateway customerGateway;

    public CreateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer execute(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        validateExistsFields(customer, customerGateway);
        return customerGateway.save(customer);
    }

    private void validateExistsFields(Customer customer, CustomerGateway customerGateway) {
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
