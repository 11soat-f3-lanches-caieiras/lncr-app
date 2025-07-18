package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.commons.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.commons.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;

public class UpdateKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public UpdateKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder updateStatus(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder) {
        Logger.info("Iniciando atualização de preparo, id: " + kitchenOrderId + ", novo status: " + newStatus + ", forçar atualização: " + forceUpdate + ", atualizar pedido do cliente: " + updateCustomerOrder);
        KitchenOrder kitchenOrder = kitchenOrderGateway.getKitchenOrderById(kitchenOrderId);
        if (kitchenOrder != null) {
            kitchenOrder.setStatus(newStatus, forceUpdate);
            kitchenOrder = this.kitchenOrderGateway.saveKitchenOrder(kitchenOrder);
            updateCustomerOrder(kitchenOrder, updateCustomerOrder);
            sendNotification(kitchenOrder);
            Logger.info("Preparo atualizado com sucesso, id: " + kitchenOrderId + ", novo status: " + newStatus);
            return kitchenOrder;
        }
         throw new KitchenOrderException("Não encontrado preparo para o id:" + kitchenOrderId, 404);
    }
    private void updateCustomerOrder(KitchenOrder kitchenOrder, Boolean updateCustomerOrder) {
        Logger.debug("Atualizando status do pedido do cliente, id: " + kitchenOrder.getCustomerOrderId() + ", novo status: " + kitchenOrder.getStatus() + ", atualizar pedido do cliente: " + updateCustomerOrder);
        if (updateCustomerOrder)
            if (kitchenOrder.getStatus().equals(KitchenOrderStatus.PREPARING.getDescription()) ||
                kitchenOrder.getStatus().equals(KitchenOrderStatus.READY.getDescription()))
                   this.kitchenOrderGateway.updateCustomerOrderStatus(kitchenOrder.getCustomerOrderId(), kitchenOrder.getStatus());
    }

    private void sendNotification(KitchenOrder kitchenOrder) {
        Logger.debug("Enviando notificação para o preparo, id: " + kitchenOrder.getId() + ", status: " + kitchenOrder.getStatus());
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
