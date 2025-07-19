package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.customer.Customer;

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

        return customerGateway.getAllCustomers(limit.orElse(10));
    }

    public Customer getById(Integer id) {
        Logger.info("Buscando cliente com ID: " + id);
        Customer customer = customerGateway.getCustomerById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        Logger.info("Cliente encontrado: " + customer.toString());
        return customer;
    }


    public Customer getByDocumentNumber(String documentNumber) {
        Logger.info("Buscando cliente com número de documento: " + documentNumber);
        Customer customer = customerGateway.getCustomerByDocumentNumber(documentNumber);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o número de documento: " + documentNumber, 404);
        }
        Logger.info("Cliente encontrado: " + customer.toString());
        return customer;
    }

    public List<Customer> getByIdList(List<Integer> customerIdList) {
        Logger.info("Buscando clientes com IDs: " + customerIdList);
        List<Customer> customer = customerGateway.getCustomerByIdList(customerIdList);
        if (customer == null){
            throw new CustomerException("Não foram encontrados clientes com os id: " + customerIdList, 404);
        }
        Logger.info("Clientes encontrados: " + customer.toString());
        return customer;
    }
}
