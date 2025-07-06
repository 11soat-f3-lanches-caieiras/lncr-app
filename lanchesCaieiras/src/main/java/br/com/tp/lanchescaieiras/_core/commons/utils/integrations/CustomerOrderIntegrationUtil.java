package br.com.tp.lanchescaieiras._core.commons.utils.integrations;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

import java.util.List;

public class CustomerOrderIntegrationUtil {

    public static CustomerOrderCustomer getCustomerDetail(Integer customerId, CustomerOrderGateway customerOrderGateway){
        return customerOrderGateway.getCustomerDetails(customerId);
    }

    public static List<CustomerOrderFoodItem> getFoodItemDetails(List<Integer> foodItemListIds, CustomerOrderGateway customerOrderGateway){
        return customerOrderGateway.getFoodItemsDetails(foodItemListIds);
    }

}
