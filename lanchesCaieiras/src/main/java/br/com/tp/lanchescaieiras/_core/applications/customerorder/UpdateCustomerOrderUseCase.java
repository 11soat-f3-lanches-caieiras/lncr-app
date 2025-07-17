package br.com.tp.lanchescaieiras._core.applications.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.enums.CustomerOrderStatus;
import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerOrderException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;

public class UpdateCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public UpdateCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder updateStatusById(Integer customerOrderId, String newStatus, Boolean forceUpdate) {
        Logger.info("Iniciando atualização de status do pedido com id: " + customerOrderId + " para o status: " + newStatus);
        CustomerOrder updateCustomerOrder= this.customerOrderGateway.getCustomerOrderById(customerOrderId);

        if (updateCustomerOrder != null) {
            String oldStatus = updateCustomerOrder.getStatus();
            updateCustomerOrder.setStatus(newStatus, forceUpdate);
            updateCustomerOrder = this.customerOrderGateway.updateCustomerOrder(updateCustomerOrder);

            switch (updateCustomerOrder.getStatus().toUpperCase()) {
                case "CANCELLED":
                    updateStatusToCancel(updateCustomerOrder,oldStatus);
                    break;
                case "RECEIVED":
                    updateStatusToReceived(updateCustomerOrder);
                    break;
                case "PREPARING","READY", "FINISHED":
                    CustomerOrderUseCaseUtils.sendNotification(updateCustomerOrder,this.customerOrderGateway);
                    break;
            }
            CustomerOrderUseCaseUtils.getCustomerDetails(updateCustomerOrder, this.customerOrderGateway);
            Logger.info("Status do pedido atualizado com sucesso: " + updateCustomerOrder.getId() + " para o status: " + updateCustomerOrder.getStatus());
            return updateCustomerOrder;
        }
        throw new CustomerOrderException("Não encontrado pedido com o id: " +customerOrderId,404);
    }

    private void updateStatusToCancel(CustomerOrder updateCustomerOrder, String oldStatus) {
        Logger.info("Atualizando status do pedido: " + updateCustomerOrder.getId() + " para Cancelled");
        try {
            this.customerOrderGateway.cancelPaymentChargeByCustomerOrderId(updateCustomerOrder.getId());
        } catch (Exception e) {
            Logger.error("Erro ao cancelar cobrança do pedido: " + updateCustomerOrder.getId());
            Logger.error(e.getMessage());
        }
        if (oldStatus.equals(CustomerOrderStatus.RECEIVED.getDescription())) {
            KitchenOrderDTO kitchenOrderDTO = this.customerOrderGateway.getKitchenOrderByCustomerOrderId(updateCustomerOrder.getId());
            if (kitchenOrderDTO != null) {
                try {
                    this.customerOrderGateway.cancelKitchenOrderById(kitchenOrderDTO.getId());
                }catch (Exception e) {
                    Logger.error("Erro ao cancelar pedido na cozinha: " + kitchenOrderDTO.getId());
                    Logger.error(e.getMessage());
                }
            }
        }
        CustomerOrderUseCaseUtils.sendNotification(updateCustomerOrder,this.customerOrderGateway);
    }

    private void updateStatusToReceived(CustomerOrder updateCustomerOrder) {
        Logger.debug("Atualizando status do pedido: " + updateCustomerOrder.getId() + " para Received");
        updateCustomerOrder = this.customerOrderGateway.getCustomerOrderById(updateCustomerOrder.getId(), true);
        CustomerOrderUseCaseUtils.getFoodItemsDetails(updateCustomerOrder, this.customerOrderGateway);
        this.customerOrderGateway.createKitchenOrder(updateCustomerOrder);
        CustomerOrderUseCaseUtils.sendNotification(updateCustomerOrder,this.customerOrderGateway);
    }
}
