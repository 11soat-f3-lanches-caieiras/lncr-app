package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;

import java.util.*;

public class CustomerOrderPresenter {

    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderPresenter(CustomerOrderMapper customerOrderMapper) {
        this.customerOrderMapper = customerOrderMapper;
    }

    public CustomerOrderDTO created(CustomerOrder customerOrder) {
        customerOrder.setFoodItems(null);
        return customerOrderMapper.customerOrderToDTO(customerOrder);
    }

    public CustomerOrderDTO getById(CustomerOrder customerOrder) {
        return customerOrderMapper.customerOrderToDTO(customerOrder);
    }

    public List<CustomerOrderDTO> getByStatusList(List<CustomerOrder> customerOrderList, List<String> statusOrderList) {
        List<CustomerOrder> newCustomerOrderList = new ArrayList<>(customerOrderList);
        newCustomerOrderList.sort(Comparator
                .comparingInt((CustomerOrder o) -> statusOrderMap(statusOrderList).getOrDefault(o.getStatus(), Integer.MAX_VALUE))
                .thenComparing(CustomerOrder::get_created));
        return newCustomerOrderList.stream().map(customerOrderMapper::customerOrderToDTO).toList();
    }

    private Map<String, Integer> statusOrderMap(List<String> statusOrderList){
        Map<String,Integer> statusOrderMap = new HashMap<>();
        for (int i = 0; i < statusOrderList.size(); i++)
            statusOrderMap.put(statusOrderList.get(i),i+1);
        return statusOrderMap;
    }
}
