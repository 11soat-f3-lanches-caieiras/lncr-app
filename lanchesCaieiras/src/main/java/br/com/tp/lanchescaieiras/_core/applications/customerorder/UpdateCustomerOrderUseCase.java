package br.com.tp.lanchescaieiras._core.applications.customerorder;

import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerOrderException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.CustomerOrderUseCaseUtils;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.commons.enums.CustomerOrderStatus;

public class UpdateCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public UpdateCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder updateStatusById(Integer customerOrderId, String newStatus, Boolean forceUpdate) {
        CustomerOrder updateCustomerOrder= this.customerOrderGateway.getCustomerOrderById(customerOrderId);

        if (updateCustomerOrder != null) {
            updateCustomerOrder.setStatus(newStatus, forceUpdate);


            updateCustomerOrder = this.customerOrderGateway.updateCustomerOrder(updateCustomerOrder);
            // Se o status for RECEIVED, cria uma KitchenOrder
            if (updateCustomerOrder.getStatus().equals(CustomerOrderStatus.RECEIVED.getDescription())) {
                updateCustomerOrder = this.customerOrderGateway.getCustomerOrderById(customerOrderId, true);
                CustomerOrderUseCaseUtils.getFoodItemsDetails(updateCustomerOrder, this.customerOrderGateway);
                this.customerOrderGateway.createKitchenOrder(updateCustomerOrder);

            }
            CustomerOrderUseCaseUtils.getCustomerDetails(updateCustomerOrder, this.customerOrderGateway);
            sendNotification(updateCustomerOrder);
            return updateCustomerOrder;
        }
        throw new CustomerOrderException("Não encontrado pedido com o id: " +customerOrderId,404);

    }



    private void sendNotification(CustomerOrder updateCustomerOrder) {
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
        if (notificationType!= null  && message != null)
            this.customerOrderGateway.sendNotification(notificationType,customerOrderId,message);
    }
}
