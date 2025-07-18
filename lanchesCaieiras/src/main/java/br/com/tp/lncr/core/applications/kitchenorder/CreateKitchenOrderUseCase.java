package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.commons.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;

public class CreateKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public CreateKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder execute(KitchenOrderDTO kitchenOrderDTO) {
        Logger.info("Iniciando criação de novo preparo para o pedido id: " + kitchenOrderDTO.getCustomerOrderId());
        if (this.kitchenOrderGateway.getKitchenOrderByCustomerOrderId(kitchenOrderDTO.getCustomerOrderId()) != null) {
            throw new KitchenOrderException("Já existe um preparo para a o pedido id: " + kitchenOrderDTO.getCustomerOrderId(),409);
        }

        kitchenOrderDTO.setStatus(KitchenOrderStatus.RECEIVED.getDescription()); // Default status for new kitchen orders
        KitchenOrder newKitchenOrder = new KitchenOrder(kitchenOrderDTO);
        newKitchenOrder = kitchenOrderGateway.saveKitchenOrder(newKitchenOrder);
        this.kitchenOrderGateway.sendNotification("KITCHEN_ORDER_RECEIVED", newKitchenOrder.getId(), "Novo preparo com id: " + newKitchenOrder.getId() + ". Aguardando início do preparo.");
        Logger.info("Novo preparo criado com sucesso, id: " + newKitchenOrder.getId());
        return newKitchenOrder;
    }
}
