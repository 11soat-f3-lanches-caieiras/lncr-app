package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerSort;
import br.com.tp.lanchescaieiras._core.commons.utils.StatusOrderUtils;

import java.util.List;

public class CustomerOrderPresenter {

    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderPresenter(CustomerOrderMapper customerOrderMapper) {
        this.customerOrderMapper = customerOrderMapper;
    }

    public CustomerOrderDTO created(CustomerSort customerOrder) {
        customerOrder.setFoodItems(null);
        return customerOrderMapper.customerOrderToDTO(customerOrder);
    }

    public CustomerOrderDTO getById(CustomerSort customerOrder) {
        return customerOrderMapper.customerOrderToDTO(customerOrder);
    }

    public List<CustomerOrderDTO> getByStatusList(List<CustomerSort> customerOrderList, List<String> statusOrderList) {
        return StatusOrderUtils.sortByStatusOrder(customerOrderList, statusOrderList)
                .stream()
                .map(customerOrderMapper::customerOrderToDTO).toList();
    }

    public CustomerOrderDTO updatedStatus(CustomerSort customerOrder) {
        return this.customerOrderMapper.customerOrderToDTO(customerOrder);
    }
}
