package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.applications.customerorder.usecases.CreateCustomerOrderUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._external.dataproxy.CustomerOrderDataProxy;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderControllerImpl implements CustomerOrderController {

    private final CustomerOrderDatabase customerOrderDatabase;
    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderControllerImpl(CustomerOrderDatabase customerOrderDatabase) {
        this.customerOrderDatabase = customerOrderDatabase;
        this.customerOrderMapper = new CustomerOrderMapper();
    }

    @Override
    public CustomerOrderDTO create(CustomerOrderDataProxy customerOrderDataProxy, CustomerOrderDTO customerOrderDTO) {
        CustomerOrder customerOrder = new CreateCustomerOrderUseCase(createCustomerOrderGateway()).execute(customerOrderDTO);
        return new CustomerOrderPresenter(customerOrderMapper).created(customerOrder);
    }

    private CustomerOrderGateway createCustomerOrderGateway(){
        return new CustomerOrderGatewayImpl(this.customerOrderDatabase,this.customerOrderMapper);
    }


}

