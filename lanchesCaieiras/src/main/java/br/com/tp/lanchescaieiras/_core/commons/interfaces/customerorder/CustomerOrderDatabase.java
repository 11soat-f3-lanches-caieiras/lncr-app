package br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;

import java.util.List;

public interface CustomerOrderDatabase {

    CustomerOrderDTO save(CustomerOrderDTO customerOrderDTO);

    CustomerOrderCustomerDTO getCustomerDetails(Integer customerId);

    List<CustomerOrderCustomerDTO> getCustomerDetailsList(List<Integer> customerIdList);

    List<CustomerOrderFoodItemDTO> getFoodItemsDetailsList(List<Integer> foodItemListIds);

    void createPaymentCharge(Integer customerOrderId, Double totalCost);

    void sendNotification(String notificationSource, Integer artefactId, String message);

    CustomerOrderDTO getCustomerOrderById(Integer customerOrderId, Boolean includFoodItems);

    List<CustomerOrderDTO> getCustomerOrderByStatusList(List<Integer> statusListIds, Boolean includeFoodItems);

    CustomerOrderDTO updateCustomerOrder(CustomerOrderDTO updatedCustomerOrderDTO);

}
