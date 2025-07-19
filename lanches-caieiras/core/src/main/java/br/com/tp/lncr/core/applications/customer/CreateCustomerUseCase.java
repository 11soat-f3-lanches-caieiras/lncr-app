package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.customer.Customer;

public class CreateCustomerUseCase {

    public final CustomerGateway customerGateway;

    public CreateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer execute(CustomerDTO customerDTO) {
        Logger.info("Iniciando criação de cliente: " + customerDTO.getName());
        Customer customer = new Customer(customerDTO);
        validateExistsFields(customer, customerGateway);
        Logger.info("Finalizando criação de cliente: " + customerDTO.getName());
        return customerGateway.save(customer);
    }

    private void validateExistsFields(Customer customer, CustomerGateway customerGateway) {
        existsByDocumentNumber(customer, customerGateway);
        existsByEmail(customer, customerGateway);
    }

    private void existsByDocumentNumber(Customer customer, CustomerGateway customerGateway) {
        Logger.debug("Verificando se o cliente já existe pelo número de documento: " + customer.getDocumentNumber());
        if (customerGateway.existsByDocumentNumber(customer.getDocumentNumber())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo número de documento: " + customer.getDocumentNumber(), 409);
        }
    }

    private void existsByEmail(Customer customer, CustomerGateway customerGateway) {
        Logger.debug("Verificando se o cliente já existe pelo e-mail: " + customer.getEmail());
        if (customerGateway.existsByEmail(customer.getEmail())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo e-mail: " + customer.getEmail(), 409);
        }
    }
}
