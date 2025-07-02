package br.com.tp.lanchescaieiras.customer.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.customer.application.usecases.DeleteCustomerUseCase;
import br.com.tp.lanchescaieiras.customer.application.usecases.GetCustomerUseCase;
import br.com.tp.lanchescaieiras.customer.application.usecases.PartialUpdateCustomerUseCase;
import br.com.tp.lanchescaieiras.customer.external.config.CustomerConfig;
import br.com.tp.lanchescaieiras.customer.application.usecases.CreateCustomerUseCase;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.CustomerDatabase;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class CustomerControllerImpl implements CustomerController {

    public CustomerControllerImpl() {
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDto, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase();
        CustomerMapper customerMapper = new CustomerMapper();
        customerDto = createCustomerUseCase.execute(customerDto, customerGateway, customerMapper);
        return customerDto;

    }

    @Override
    public List<CustomerDTO> getAll(Optional<Integer> _limit, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        List<CustomerDTO> customerDTOList = new GetCustomerUseCase().getAll(_limit, customerGateway, customerMapper);
        return customerDTOList;
    }

    @Override
    public CustomerDTO getById(Integer id, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        CustomerDTO customerDTO = new GetCustomerUseCase().getById(id, customerGateway, customerMapper);
        return customerDTO;
    }

    @Override
    public CustomerDTO getByDocumentNumber(String documentNumber, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        CustomerDTO customerDTO = new GetCustomerUseCase().getByDocumentNumber(documentNumber, customerGateway, customerMapper);
        return customerDTO;
    }

    @Override
    public CustomerDTO partialUpdateById(Integer id, CustomerDTO customerDTO, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        customerDTO = new PartialUpdateCustomerUseCase().execute(id, customerDTO, customerGateway, customerMapper);
        return customerDTO;
    }

    @Override
    public void delete(Integer id, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        DeleteCustomerUseCase deleteCustomerUseCase = new DeleteCustomerUseCase();
        deleteCustomerUseCase.execute(id, customerGateway, customerMapper);
    }
}
