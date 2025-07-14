package br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;

import java.util.List;

public interface KitchenOrderController {
    KitchenOrderDTO createKitchenOrder(KitchenOrderDTO kitchenOrderDTO);

    KitchenOrderDTO getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems);

    KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);

    KitchenOrderDTO updateOrderStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder);

    List<KitchenOrderDTO> getKitchenOrderByStatusList(List<String> statusList, Boolean includeFoodItems);
}
