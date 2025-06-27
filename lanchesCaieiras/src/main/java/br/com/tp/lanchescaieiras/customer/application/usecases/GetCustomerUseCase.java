package br.com.tp.lanchescaieiras.customer.application.usecases;

import br.com.tp.lanchescaieiras.customer.adapters.CustomerGatewayImpl;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerGateway;
import br.com.tp.lanchescaieiras.customer.application.mappers.CustomerDtoMapper;
import br.com.tp.lanchescaieiras.customer.domain.entities.Customer;
import br.com.tp.lanchescaieiras.customer.domain.shared.exceptions.CustomerException;

import java.util.List;
import java.util.Optional;

public class GetCustomerUseCase {

    private CustomerDtoMapper customerDtoMapper;

    public GetCustomerUseCase() {
        this.customerDtoMapper = new CustomerDtoMapper();
    }

    public CustomerDTO getById(Integer id, CustomerGateway customerGateway) {
        Customer customer = customerGateway.findById(id);
        if (customer==null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        return customerDtoMapper.domainToDto(customer);
    }
    public boolean existsByDocumentNumber(CustomerDTO customerDTO, CustomerGateway customerGateway) {
        return customerGateway.existsByDocumentNumber(customerDTO.getDocumentNumber());
    }

    public boolean existsByEmail(CustomerDTO customerDTO, CustomerGateway customerGateway) {
        return customerGateway.existsByEmail(customerDTO.getEmail());
    }

    public CustomerDTO getByDocumentNumber(String documentNumber, CustomerGatewayImpl customerGateway) {
        Customer customer = customerGateway.findByDocumentNumber(documentNumber);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o número de documento: " + documentNumber, 404);
        }
        return customerDtoMapper.domainToDto(customer);
    }

    public List<CustomerDTO> getAll(Optional<Integer> limit, CustomerGatewayImpl customerGateway) {
        if (limit.isPresent() && (limit.get() <= 0 || limit.get() > 50)) {
            throw new CustomerException("Limite deve ser maior que 0 e menor ou igual a 50",400);
        }
        List<Customer> customers = customerGateway.findAll(limit.orElse(10));

        return customers.stream()
                .map(customerDtoMapper::domainToDto)
                .toList();
    }

    public CustomerDTO deleteById(Integer id, CustomerGatewayImpl customerGateway) {
        Customer customer = customerGateway.findById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        customerGateway.deleteById(id);
        return customerDtoMapper.domainToDto(customer);
    }
}
