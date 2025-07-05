package br.com.tp.lanchescaieiras._core.domain.customerorder;

import java.util.List;

public interface CustomerOrderFoodItemRepository {

    CustomerOrderFoodItem save(CustomerOrderFoodItem foodItem, Integer id);

    List<CustomerOrderFoodItem> findByCustomerOrderId(Integer customerOrderId);

}
