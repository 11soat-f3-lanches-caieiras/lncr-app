package br.com.tp.lanchescaieiras._core.commons.utils;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderUseCaseUtils {

    public static void getCustomerDetails(CustomerOrder customerOrder, CustomerOrderGateway customerOrderGateway){
        //Validação somente quando é informado id do cliente. Requer um id válido
        if (customerOrder != null && customerOrder.getCustomer() != null && customerOrder.getCustomer().getId() != null) {
            Integer customerId = customerOrder.getCustomer().getId();
            customerOrder.setCustomer(customerOrderGateway.getCustomerDetails(customerId));
            if (customerOrder.getCustomer() ==null){
                throw new CustomerOrderException("Cliente com id "+ customerId +" não encontrado. Envie um pedido com cliente válido.",400);
            }
        }
    }

    public static void getCustomerDetailsList(List<CustomerOrder> customerOrderList, CustomerOrderGateway customerOrderGateway){
        if (customerOrderList != null && !customerOrderList.isEmpty()) {
            List<Integer> customerIds = getCustomerIds(customerOrderList);
            if (customerIds != null) {
                {
                    List<CustomerOrderCustomer> customerDetailsList = customerOrderGateway.getCustomerDetailsList(customerIds);
                    for (CustomerOrder customerOrder : customerOrderList) {
                        if (customerOrder.getCustomer() != null && customerOrder.getCustomer().getId() != null)
                            customerOrder.setCustomer(mergeCustomerDetails(customerOrder.getCustomer().getId(),customerDetailsList));
                    }
                }
            }
        }
    }

    public static void getFoodItemsDetailsList(List<CustomerOrder> customerOrderList, CustomerOrderGateway customerOrderGateway){
        if (customerOrderList != null && !customerOrderList.isEmpty()) {
            List<Integer> foodItemIdList = getFoodItemsIds(customerOrderList);
            if (foodItemIdList != null) {
                {
                    List<CustomerOrderFoodItem> foodItemDetails = customerOrderGateway.getFoodItemsDetails(foodItemIdList);
                    for (CustomerOrder customerOrder : customerOrderList) {
                        if (customerOrder.getFoodItems() != null || !customerOrder.getFoodItems().isEmpty())
                            customerOrder.setFoodItems(mergeFoodItemsDetails(customerOrder.getFoodItems(), foodItemDetails));
                    }
                }
            }
        }
    }

    public static void getFoodItemsDetails(CustomerOrder customerOrder, CustomerOrderGateway customerOrderGateway){
        if (customerOrder != null) {
            List<Integer> foodItemIdList = getFoodItemsIds(customerOrder);
            if (foodItemIdList != null) {
                {
                    List<CustomerOrderFoodItem> foodItemDetails = customerOrderGateway.getFoodItemsDetails(foodItemIdList);
                    if (customerOrder.getFoodItems() != null && foodItemDetails != null)
                            customerOrder.setFoodItems(mergeFoodItemsDetails(customerOrder.getFoodItems(), foodItemDetails));
                }
            }
        }
    }

    private static List<Integer> getCustomerIds(List<CustomerOrder> customerOrderList){
        if (customerOrderList != null && !customerOrderList.isEmpty()) {
            return customerOrderList.stream()
                    .map(CustomerOrder::getCustomer)
                    .filter(customer -> customer != null)
                    .map(CustomerOrderCustomer::getId)
                    .filter(id -> id != null)
                    .distinct()
                    .collect(Collectors.toList());
        }
        return null;
    }

    private static List<Integer> getFoodItemsIds(List<CustomerOrder> customerOrderList){
        if (customerOrderList != null && !customerOrderList.isEmpty()) {
            return customerOrderList.stream()
                    .flatMap(order -> order.getFoodItems().stream())
                    .map(CustomerOrderFoodItem::getId).distinct().collect(Collectors.toList());
        }
        return null;
    }



    private static List<Integer> getFoodItemsIds(CustomerOrder customerOrder){
        if (customerOrder.getFoodItems()!= null && !customerOrder.getFoodItems().isEmpty()) {
            return customerOrder.getFoodItems().stream()
                    .map(CustomerOrderFoodItem::getId).distinct().collect(Collectors.toList());
        }
        return null;
    }

    private static List<CustomerOrderFoodItem> mergeFoodItemsDetails(List<CustomerOrderFoodItem> orderItems, List<CustomerOrderFoodItem> foodItemDetails) {
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

    private static CustomerOrderCustomer mergeCustomerDetails(Integer customerId, List<CustomerOrderCustomer> customerDetailsList) {
        CustomerOrderCustomer customerDetailed = customerDetailsList.stream()
                .filter(customerdetail -> customerdetail.getId() != null && customerdetail.getId().equals(customerId))
                .findFirst()
                .orElse(null);
        return new CustomerOrderCustomer(customerDetailed.getId(),customerDetailed.getName());


    }
}
