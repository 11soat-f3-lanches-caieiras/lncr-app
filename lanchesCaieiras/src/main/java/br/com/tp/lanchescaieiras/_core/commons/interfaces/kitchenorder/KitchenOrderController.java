package br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;

public interface KitchenOrderController {
    KitchenOrderDTO createKitchenOrder(KitchenOrderDTO kitchenOrderDTO);

    KitchenOrderDTO getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems);

    KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);
}
