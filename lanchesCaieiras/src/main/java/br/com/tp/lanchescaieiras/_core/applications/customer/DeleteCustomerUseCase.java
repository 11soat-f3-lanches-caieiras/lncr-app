package br.com.tp.lanchescaieiras._core.applications.customer;

import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.customer.Customer;

public class DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DeleteCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(Integer id) {
        Logger.info("Executando a exclusão do cliente com ID: " + id);
        Customer customer = customerGateway.findById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
    }
}
