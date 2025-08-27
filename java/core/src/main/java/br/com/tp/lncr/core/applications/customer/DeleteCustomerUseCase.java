package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.customer.Customer;

public class DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DeleteCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(Integer id) {
        Logger.info("Executando a exclusão do cliente com ID: " + id);
        Customer customer = customerGateway.getCustomerById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
    }
}
