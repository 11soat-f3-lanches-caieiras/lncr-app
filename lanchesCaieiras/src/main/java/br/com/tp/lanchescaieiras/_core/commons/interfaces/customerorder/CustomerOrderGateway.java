package br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

import java.util.List;

public interface CustomerOrderGateway {

    CustomerOrder createCustomerOrder(CustomerOrder customerOrder);

    CustomerOrderCustomer getCustomerDetails(Integer customerId);

    List<CustomerOrderFoodItem> getFoodItemsDetails(List<Integer> foodItemListIds);

    /*CustomerOrder getCustomerOrderById(Integer id, Boolean includeFoodItems);

    List<CustomerOrder> getCustomerOrderByStatus(String status, Boolean includeFoodItems);

    List<CustomerOrder> getAllCustomerOrders(String status, Boolean includeFoodItems);

    CustomerOrder updateOrderStatusById(Integer id, String newStatus, Boolean forceUpdate);*/
}
