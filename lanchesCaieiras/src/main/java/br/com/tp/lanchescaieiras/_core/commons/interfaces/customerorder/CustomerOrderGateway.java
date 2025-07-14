package br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerSort;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

import java.util.List;

public interface CustomerOrderGateway {

    CustomerSort createCustomerOrder(CustomerSort customerOrder);

    CustomerOrderCustomer getCustomerDetails(Integer customerIdList);

    List<CustomerOrderCustomer> getCustomerDetailsList(List<Integer> customerIdList);

    List<CustomerOrderFoodItem> getFoodItemsDetails(List<Integer> foodItemListIds);

    void createPaymentCharge(CustomerSort customerOrder);

    void sendNotification(String notificationSource, Integer artefactId, String message);

    CustomerSort getCustomerOrderById(Integer customerOrderId);

    CustomerSort getCustomerOrderById(Integer customerOrderId, Boolean includFoodItems);

    List<CustomerSort> getCustomerOrderByStatusList(List<Integer> statusListIds, Boolean includeFoodItems);

    CustomerSort updateCustomerOrder(CustomerSort updateCustomerOrder);

    void createKitchenOrder(CustomerSort updateCustomerOrder);



    /*CustomerOrder getCustomerOrderById(Integer id, Boolean includeFoodItems);

    List<CustomerOrder> getCustomerOrderByStatus(String status, Boolean includeFoodItems);

    List<CustomerOrder> getAllCustomerOrders(String status, Boolean includeFoodItems);

    CustomerOrder updateOrderStatusById(Integer id, String newStatus, Boolean forceUpdate);*/
}
