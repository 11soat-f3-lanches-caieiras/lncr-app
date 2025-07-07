package br.com.tp.lanchescaieiras._core.commons.utils;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderUseCaseUtils {

    public static void getCustomerDetails(CustomerOrder customerOrder, CustomerOrderGateway customerOrderGateway){
        //Validação somente quando é informado id do cliente. Requer um id válido
        if (customerOrder.getCustomer() != null && customerOrder.getCustomer().getId() != null) {
            Integer customerId = customerOrder.getCustomer().getId();
            customerOrder.setCustomer(customerOrderGateway.getCustomerDetails(customerId));
            if (customerOrder.getCustomer() ==null){
                throw new CustomerOrderException("Cliente com id "+ customerId +" não encontrado. Envie um pedido com cliente válido.",400);
            }
        }
    }

    public static void getFoodItemsDetails(CustomerOrder customerOrder, CustomerOrderGateway customerOrderGateway){
        List<Integer> foodItemsIds = customerOrder.getFoodItems().stream().map(CustomerOrderFoodItem::getId).distinct().collect(Collectors.toList());
        List<CustomerOrderFoodItem> foodItemDetails = customerOrderGateway.getFoodItemsDetails(foodItemsIds);
        customerOrder.setFoodItems(mergeDetails(customerOrder.getFoodItems(),foodItemDetails));
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

