package br.com.tp.lanchescaieiras._core.applications.kitchenorder.usecases;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.KitchenOrderException;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderStatus;

public class CreateKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public CreateKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder execute(KitchenOrderDTO kitchenOrderDTO) {
        if (this.kitchenOrderGateway.getKitchenOrderByCustomerOrderId(kitchenOrderDTO.getCustomerOrderId()) != null) {
            throw new KitchenOrderException("Já existe um preparo para a o pedido id: " + kitchenOrderDTO.getCustomerOrderId(),409);
        }

        kitchenOrderDTO.setStatus(KitchenOrderStatus.RECEIVED.getDescription()); // Default status for new kitchen orders
        KitchenOrder newKitchenOrder = new KitchenOrder(kitchenOrderDTO);
        newKitchenOrder = kitchenOrderGateway.save(newKitchenOrder);
        return newKitchenOrder;
    }
}
