package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.applications.customerorder.usecases.CreateCustomerOrderUseCase;
import br.com.tp.lanchescaieiras._core.applications.customerorder.usecases.GetCustomerOrderUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderControllerImpl implements CustomerOrderController {

    private final CustomerOrderDatabase customerOrderDatabase;
    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderControllerImpl(CustomerOrderDatabase customerOrderDatabase) {
        this.customerOrderDatabase = customerOrderDatabase;
        this.customerOrderMapper = new CustomerOrderMapper();
    }

    @Override
    public CustomerOrderDTO create(CustomerOrderDatabase customerOrderDatabase, CustomerOrderDTO customerOrderDTO) {
        CustomerOrder customerOrder = new CreateCustomerOrderUseCase(createCustomerOrderGateway()).execute(customerOrderDTO);
        return new CustomerOrderPresenter(customerOrderMapper).created(customerOrder);
    }

    @Override
    public CustomerOrderDTO getById(CustomerOrderDatabase customerOrderDatabase, Integer customerOrderId, Boolean includFoodItems) {
        CustomerOrder customerOrder = new GetCustomerOrderUseCase(createCustomerOrderGateway()).getById(customerOrderId,includFoodItems);
        return new CustomerOrderPresenter(customerOrderMapper).getById(customerOrder);
    }

    @Override
    public List<CustomerOrderDTO> getByStatusList(CustomerOrderDatabase customerOrderDatabase, List<String> statusList, Boolean includeFoodItems) {
        List<CustomerOrder> customerOrderList = new GetCustomerOrderUseCase(createCustomerOrderGateway()).getByStatusList(statusList,includeFoodItems);
        return new CustomerOrderPresenter(customerOrderMapper).getByStatusList(customerOrderList, statusList);
    }

    private CustomerOrderGateway createCustomerOrderGateway(){
        return new CustomerOrderGatewayImpl(this.customerOrderDatabase,this.customerOrderMapper);
    }


}

