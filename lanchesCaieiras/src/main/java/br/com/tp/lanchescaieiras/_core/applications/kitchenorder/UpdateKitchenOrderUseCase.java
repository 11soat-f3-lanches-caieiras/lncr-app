package br.com.tp.lanchescaieiras._core.applications.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.KitchenOrderException;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderStatus;

public class UpdateKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public UpdateKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder updateStatus(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder) {
        KitchenOrder kitchenOrder = kitchenOrderGateway.getKitchenOrderById(kitchenOrderId);
        if (kitchenOrder != null) {
            kitchenOrder.setStatus(newStatus, forceUpdate);
            kitchenOrder = this.kitchenOrderGateway.save(kitchenOrder);
            updateCustomerOrder(kitchenOrder, updateCustomerOrder);
            sendNotification(kitchenOrder);
            return kitchenOrder;
        }
         throw new KitchenOrderException("Não encontrado preparo para o id:" + kitchenOrderId, 404);
    }
    private void updateCustomerOrder(KitchenOrder kitchenOrder, Boolean updateCustomerOrder) {
        if (updateCustomerOrder)
            if (kitchenOrder.getStatus().equals(KitchenOrderStatus.PREPARING.getDescription()) ||
                kitchenOrder.getStatus().equals(KitchenOrderStatus.READY.getDescription()))
                   this.kitchenOrderGateway.updateCustomerOrderStatus(kitchenOrder.getCustomerOrderId(), kitchenOrder.getStatus());
    }

    private void sendNotification(KitchenOrder kitchenOrder) {
        if (kitchenOrder == null) return;
        Integer customerOrderId = kitchenOrder.getId();
        String  notificationType = null;
        String  message = null;
        switch (kitchenOrder.getStatus().toUpperCase()) {
            case "PREPARING":
                notificationType = "KITCHEN_ORDER_PREPARING";
                message = "Preparo com id: " + customerOrderId + " iniciado.";
                break;
            case "READY":
                notificationType = "KITCHEN_ORDER_READY";
                message = "Preparo com id: " + customerOrderId + " pronto.";
                break;
            case "FINISEHD":
                notificationType = "KITCHEN_ORDER_FINISHED";
                message = "Preparo com id: " + customerOrderId + " finalizado.";
                break;
            case "CANCELLED":
                notificationType = "KITCHEN_ORDER_CANCELLED";
                message = "Preparo com id: " + customerOrderId + " cancelado.";
            default:
                break;
        }
        if (notificationType!= null  && message != null)
            this.kitchenOrderGateway.sendNotification(notificationType,customerOrderId,message);
    }
}
