package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;

public class CustomerOrderPresenter {

    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderPresenter(CustomerOrderMapper customerOrderMapper) {
        this.customerOrderMapper = customerOrderMapper;
    }

    public CustomerOrderDTO created(CustomerOrder customerOrder) {
        customerOrder.setFoodItems(null);
        return customerOrderMapper.customerOrderToDTO(customerOrder);
    }
}
