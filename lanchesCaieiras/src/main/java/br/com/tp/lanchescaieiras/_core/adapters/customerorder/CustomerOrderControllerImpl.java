package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.applications.customerorder.CreateCustomerOrderUseCase;
import br.com.tp.lanchescaieiras._core.applications.customerorder.GetCustomerOrderUseCase;
import br.com.tp.lanchescaieiras._core.applications.customerorder.UpdateCustomerOrderUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerSort;

import java.util.List;

public class CustomerOrderControllerImpl implements CustomerOrderController {

    private final CustomerOrderGateway customerOrderGateway;
    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderControllerImpl(CustomerOrderDatabase customerOrderDatabase) {
        this.customerOrderMapper = new CustomerOrderMapper();
        this.customerOrderGateway = new CustomerOrderGatewayImpl(customerOrderDatabase,this.customerOrderMapper);
        
    }

    @Override
    public CustomerOrderDTO create(CustomerOrderDatabase customerOrderDatabase, CustomerOrderDTO customerOrderDTO) {
        CustomerSort customerOrder = new CreateCustomerOrderUseCase(customerOrderGateway).execute(customerOrderDTO);
        return new CustomerOrderPresenter(customerOrderMapper).created(customerOrder);
    }

    @Override
    public CustomerOrderDTO getById(CustomerOrderDatabase customerOrderDatabase, Integer customerOrderId, Boolean includFoodItems) {
        CustomerSort customerOrder = new GetCustomerOrderUseCase(customerOrderGateway).getById(customerOrderId,includFoodItems);
        return new CustomerOrderPresenter(customerOrderMapper).getById(customerOrder);
    }

    @Override
    public List<CustomerOrderDTO> getByStatusList(CustomerOrderDatabase customerOrderDatabase, List<String> statusList, Boolean includeFoodItems) {
        List<CustomerSort> customerOrderList = new GetCustomerOrderUseCase(customerOrderGateway).getByStatusList(statusList,includeFoodItems);
        return new CustomerOrderPresenter(customerOrderMapper).getByStatusList(customerOrderList, statusList);
    }

    @Override
    public CustomerOrderDTO updateStatusById(CustomerOrderDatabase customerOrderDatabase, Integer customerOrderId, String newStatus, Boolean forceUpdate) {
        CustomerSort customerOrder = new UpdateCustomerOrderUseCase(customerOrderGateway).updateStatusById(customerOrderId,newStatus,forceUpdate);
        return new CustomerOrderPresenter(customerOrderMapper).updatedStatus(customerOrder);
    }

 


}

