package br.com.tp.lanchescaieiras.customer.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
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
    public ResponseEntity<Response<CustomerDTO>> create(CustomerDTO customerDto, CustomerDatabase customerDatabase, CustomerConfig customerConfig) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase();
        customerDto = createCustomerUseCase.createCustomer(customerDto, customerGateway);

        CustomerPresenter customerPresenter = new CustomerPresenter();
        return customerPresenter.created(customerDto, customerConfig);

    }

    @Override
    public ResponseEntity<ResponseList<CustomerDTO>> getAll(Optional<Integer> _limit, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        List<CustomerDTO> customerDTOList = getCustomerUseCase.getAll(_limit, customerGateway);

        CustomerPresenter customerPresenter = new CustomerPresenter();
        return customerPresenter.getAll(customerDTOList);
    }

    @Override
    public ResponseEntity<Response<CustomerDTO>> getById(Integer id, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        CustomerDTO customerDTO = getCustomerUseCase.getById(id, customerGateway);

        CustomerPresenter customerPresenter = new CustomerPresenter();
        return customerPresenter.getById(customerDTO);
    }

    @Override
    public ResponseEntity<Response<CustomerDTO>> getByDocumentNumber(String documentNumber, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        CustomerDTO customerDTO = getCustomerUseCase.getByDocumentNumber(documentNumber, customerGateway);

        CustomerPresenter customerPresenter = new CustomerPresenter();
        return customerPresenter.getByDocumentNumber(customerDTO);
    }

    @Override
    public ResponseEntity<Response<CustomerDTO>> partialUpdateById(Integer id, CustomerDTO customerDTO, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        PartialUpdateCustomerUseCase partialUpdateCustomerUseCase = new PartialUpdateCustomerUseCase();
        CustomerDTO updatedCustomerDTO = partialUpdateCustomerUseCase.partialUpdateById(id, customerDTO, customerGateway);

        CustomerPresenter customerPresenter = new CustomerPresenter();
        return customerPresenter.getById(updatedCustomerDTO);
    }

    @Override
    public ResponseEntity<Response<CustomerDTO>> delete(Integer id, CustomerDatabase customerDatabase) {
        CustomerGatewayImpl customerGateway = new CustomerGatewayImpl(customerDatabase);

        GetCustomerUseCase getCustomerUseCase = new GetCustomerUseCase();
        CustomerDTO customerDTO = getCustomerUseCase.deleteById(id, customerGateway);

        CustomerPresenter customerPresenter = new CustomerPresenter();
        return customerPresenter.deleted(customerDTO);
    }
}
