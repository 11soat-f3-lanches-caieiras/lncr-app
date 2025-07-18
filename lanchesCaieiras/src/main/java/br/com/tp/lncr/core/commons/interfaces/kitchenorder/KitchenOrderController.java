package br.com.tp.lncr.core.commons.interfaces.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;

import java.util.List;

public interface KitchenOrderController {
    KitchenOrderDTO createKitchenOrder(KitchenOrderDTO kitchenOrderDTO);

    KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);

    KitchenOrderDTO getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems);

    List<KitchenOrderDTO> getKitchenOrderByStatusList(List<String> statusList, Boolean includeFoodItems);

    KitchenOrderDTO updateOrderStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder);
}
