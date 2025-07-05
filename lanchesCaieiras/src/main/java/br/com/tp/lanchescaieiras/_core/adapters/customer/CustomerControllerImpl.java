package br.com.tp.lanchescaieiras._core.adapters.customer;

import br.com.tp.lanchescaieiras._core.applications.customer.CreateCustomerUseCase;
import br.com.tp.lanchescaieiras._core.applications.customer.DeleteCustomerUseCase;
import br.com.tp.lanchescaieiras._core.applications.customer.GetCustomerUseCase;
import br.com.tp.lanchescaieiras._core.applications.customer.PartialUpdateCustomerUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerDatabase;
import br.com.tp.lanchescaieiras._core.domain.customer.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerControllerImpl implements CustomerController {

    public CustomerControllerImpl() {
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDto, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerGateway);
        CustomerMapper customerMapper = new CustomerMapper();
        Customer customer = createCustomerUseCase.execute(customerDto);
        return new CustomerPresenter(customerMapper).created(customer);

    }

    @Override
    public List<CustomerDTO> getAll(Optional<Integer> _limit, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        List<Customer> customerList = new GetCustomerUseCase(customerGateway).getAll(_limit);
        return new CustomerPresenter(customerMapper).getAll(customerList);
    }

    @Override
    public CustomerDTO getById(Integer id, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        Customer customer = new GetCustomerUseCase(customerGateway).getById(id);
        return new CustomerPresenter(customerMapper).getbyId(customer);
    }

    @Override
    public CustomerDTO getByDocumentNumber(String documentNumber, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        Customer customer = new GetCustomerUseCase(customerGateway).getByDocumentNumber(documentNumber);
        return new CustomerPresenter(customerMapper).getByDocumentNumber(customer);
    }

    @Override
    public CustomerDTO partialUpdateById(Integer id, CustomerDTO customerDTO, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        Customer customer = new PartialUpdateCustomerUseCase(customerGateway).execute(id, customerDTO);
        CustomerMapper customerMapper = new CustomerMapper();
        return new CustomerPresenter(customerMapper).partialUpdatedById(customer);
    }

    @Override
    public void delete(Integer id, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);
        CustomerMapper customerMapper = new CustomerMapper();
        new DeleteCustomerUseCase(customerGateway).execute(id);
    }
}
