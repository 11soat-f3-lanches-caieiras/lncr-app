package br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;

import java.util.List;

public interface CustomerOrderDatabase {

    CustomerOrderDTO save(CustomerOrderDTO customerOrderDTO);

    CustomerOrderCustomerDTO getCustomerDetails(Integer customerId);

    List<CustomerOrderFoodItemDTO> getFoodItemsDetails(List<Integer> foodItemListIds);

    /*CustomerOrder findById(Integer id);

    List<CustomerOrder> findByStatusId(Integer statusId);

    CustomerOrder updateStatusById(Integer id, Integer statusId);

    CustomerOrderFoodItem save(CustomerOrderFoodItem foodItem, Integer id);

    List<CustomerOrderFoodItem> findByCustomerOrderId(Integer customerOrderId);*/
}
