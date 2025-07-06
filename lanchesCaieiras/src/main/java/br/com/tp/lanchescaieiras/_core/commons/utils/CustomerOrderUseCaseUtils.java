package br.com.tp.lanchescaieiras._core.commons.utils;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.integrations.CustomerOrderIntegrationUtil;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderUseCaseUtils {

    public static CustomerOrder getFoodItemsDetails(CustomerOrder customerOrder, CustomerOrderGateway customerOrderGateway){

        List<Integer> foodItemsIds = customerOrder.getFoodItems().stream().map(CustomerOrderFoodItem::getId).distinct().collect(Collectors.toList());
        List<CustomerOrderFoodItem> foodItemDetails = CustomerOrderIntegrationUtil.getFoodItemDetails(foodItemsIds,customerOrderGateway);
        customerOrder.setFoodItems(mergeDetails(customerOrder.getFoodItems(),foodItemDetails));
        return customerOrder;
    }

    public static List<CustomerOrderFoodItem> mergeDetails(List<CustomerOrderFoodItem> orderItems, List<CustomerOrderFoodItem> foodItemDetails) {
        for (CustomerOrderFoodItem item : orderItems) {
            CustomerOrderFoodItem details = foodItemDetails.stream()
                .filter(f -> f.getId().equals(item.getId()))
                .findFirst()
                .orElse(null);
            if (details != null) {
                item.setName(details.getName());
                item.setDescription(details.getDescription());
                if (item.getPrice() == null) {
                    item.setPrice(details.getPrice());
                }
            }
        }
        return orderItems;
    }
}

