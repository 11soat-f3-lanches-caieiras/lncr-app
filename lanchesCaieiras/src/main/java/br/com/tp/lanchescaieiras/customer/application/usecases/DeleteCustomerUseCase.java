package br.com.tp.lanchescaieiras.customer.application.usecases;

import br.com.tp.lanchescaieiras.commons.interfaces.CustomerGateway;
import br.com.tp.lanchescaieiras.customer.adapters.CustomerMapper;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.exceptions.CustomerException;

public class DeleteCustomerUseCase {

    public void execute(Integer id, CustomerGateway customerGateway, CustomerMapper customerMapper){
        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        Customer customer = customerGateway.findById(id);
        if (customer==null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
    }
}
