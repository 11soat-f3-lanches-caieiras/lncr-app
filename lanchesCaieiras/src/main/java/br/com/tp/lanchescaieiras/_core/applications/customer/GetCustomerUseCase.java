package br.com.tp.lanchescaieiras._core.applications.customer;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lanchescaieiras._core.domain.customer.Customer;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerException;

import java.util.List;
import java.util.Optional;

public class GetCustomerUseCase {

    private final CustomerGateway customerGateway;

    public GetCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public List<Customer> getAll(Optional<Integer> limit) {
        if (limit.isPresent() && (limit.get() <= 0 || limit.get() > 50)) {
            throw new CustomerException("Limite deve ser maior que 0 e menor ou igual a 50", 400);
        }

        return customerGateway.findAll(limit.orElse(10));
    }

    public Customer getById(Integer id) {
        Customer customer = customerGateway.findById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        return customer;
    }


    public Customer getByDocumentNumber(String documentNumber) {
        Customer customer = customerGateway.findByDocumentNumber(documentNumber);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o número de documento: " + documentNumber, 404);
        }
        return customer;
    }


}
