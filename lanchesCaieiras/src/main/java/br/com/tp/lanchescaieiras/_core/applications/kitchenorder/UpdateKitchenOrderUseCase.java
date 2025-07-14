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
}
