package br.com.tp.lncr.core.adapters.customer;

import br.com.tp.lncr.core.applications.customer.CreateCustomerUseCase;
import br.com.tp.lncr.core.applications.customer.DeleteCustomerUseCase;
import br.com.tp.lncr.core.applications.customer.GetCustomerUseCase;
import br.com.tp.lncr.core.applications.customer.PartialUpdateCustomerUseCase;
import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerController;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerDatabase;
import br.com.tp.lncr.core.commons.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.domain.customer.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerControllerImpl implements CustomerController {

    private final CustomerGateway customerGateway;
    private final CustomerMapper customerMapper;

    public CustomerControllerImpl(CustomerDatabase customerDatabase) {
        this.customerMapper = new CustomerMapper();
        this.customerGateway = new CustomerGatewayImpl(customerDatabase, this.customerMapper);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDto) {
        Customer customer = new CreateCustomerUseCase(customerGateway).execute(customerDto);
        return new CustomerPresenter(customerMapper).created(customer);
    }

    @Override
    public void delete(Integer id) {
        new DeleteCustomerUseCase(customerGateway).execute(id);
    }

    @Override
    public List<CustomerDTO> getAll(Optional<Integer> _limit) {
        List<Customer> customerList = new GetCustomerUseCase(customerGateway).getAll(_limit);
        return new CustomerPresenter(customerMapper).getAll(customerList);
    }

    @Override
    public CustomerDTO getByDocumentNumber(String documentNumber) {
        Customer customer = new GetCustomerUseCase(customerGateway).getByDocumentNumber(documentNumber);
        return new CustomerPresenter(customerMapper).getByDocumentNumber(customer);
    }

    @Override
    public CustomerDTO getById(Integer id) {
        Customer customer = new GetCustomerUseCase(customerGateway).getById(id);
        return new CustomerPresenter(customerMapper).getbyId(customer);
    }

    @Override
    public List<CustomerDTO> getByIdList(List<Integer> customerIdList) {
        List<Customer> customerList = new GetCustomerUseCase(customerGateway).getByIdList(customerIdList);
        return new CustomerPresenter(customerMapper).getByIdList(customerList);
    }

    @Override
    public CustomerDTO partialUpdateById(Integer id, CustomerDTO customerDTO) {
        Customer customer = new PartialUpdateCustomerUseCase(customerGateway).execute(id, customerDTO);
        return new CustomerPresenter(customerMapper).partialUpdatedById(customer);
    }

}
