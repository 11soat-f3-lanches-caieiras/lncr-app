package br.com.tp.lanchescaieiras._core.applications.customerorder;

import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerOrderException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerOrderUseCaseUtils {

    public static void getCustomerDetails(CustomerOrder customerOrder, CustomerOrderGateway customerOrderGateway){
        Logger.debug("Iniciando busca de detalhes do cliente para o pedido.");
        if (customerOrder != null && customerOrder.getCustomer() != null && customerOrder.getCustomer().getId() != null) {
            Integer customerId = customerOrder.getCustomer().getId();
            customerOrder.setCustomer(customerOrderGateway.getCustomerDetails(customerId));
            if (customerOrder.getCustomer() ==null){
                throw new CustomerOrderException("Cliente com id "+ customerId +" não encontrado. Envie um pedido com cliente válido.",400);
            }
        }
    }

    public static void getCustomerDetailsList(List<CustomerOrder> customerOrderList, CustomerOrderGateway customerOrderGateway){
        Logger.debug("Iniciando busca de detalhes do cliente para o pedido.");
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
        Logger.debug("Iniciando busca de detalhes dos itens de comida para os pedidos.");
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
        Logger.debug("Iniciando busca de detalhes dos itens de comida para os pedidos.");
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
                    .filter(Objects::nonNull)
                    .map(CustomerOrderCustomer::getId)
                    .filter(Objects::nonNull)
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
        Logger.debug("Atualizado detalhes dos itens de comida no Pedido do cliente");
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
        Logger.debug("Atualizado detalhes dos itens de comida no Pedido do cliente");
        CustomerOrderCustomer customerDetailed = customerDetailsList.stream()
                .filter(customerdetail -> customerdetail.getId() != null && customerdetail.getId().equals(customerId))
                .findFirst()
                .orElse(null);
        return new CustomerOrderCustomer(customerDetailed.getId(),customerDetailed.getName());
    }

    public static void sendNotification(CustomerOrder updateCustomerOrder, CustomerOrderGateway customerOrderGateway) {
        if (updateCustomerOrder == null) return;
        Integer customerOrderId = updateCustomerOrder.getId();
        String  notificationType = null;
        String  message = null;
        switch (updateCustomerOrder.getStatus().toUpperCase()) {
            case "RECEIVED":
                notificationType = "CUSTOMER_ORDER_RECEIVED";
                message = "Pagamento finalizado do pedido com id: " + customerOrderId + ". Aguardando preparo.";
                break;
            case "PREPARING":
                notificationType = "CUSTOMER_ORDER_PREPARING";
                message = "Pedido com id: " + customerOrderId + " iniciou preparo.";
                break;
            case "READY":
                notificationType = "CUSTOMER_ORDER_READY";
                message = "Pedido com id: " + customerOrderId + " pronto para retirada.";
                break;
            case "FINISEHD":
                notificationType = "CUSTOMER_ORDER_FINISHED";
                message = "Pedido com id: " + customerOrderId + " finalizado.";
                break;
            case "CANCELLED":
                notificationType = "CUSTOMER_ORDER_CANCELLED";
                message = "Pedido com id: " + customerOrderId + " cancelado.";
            default:
                // Nenhuma ação
                break;
        }
        if (notificationType != null)
            customerOrderGateway.sendNotification(notificationType,customerOrderId,message);
    }
}
