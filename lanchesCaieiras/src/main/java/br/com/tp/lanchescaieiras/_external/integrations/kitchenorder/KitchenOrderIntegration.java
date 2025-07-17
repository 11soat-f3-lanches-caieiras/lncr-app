package br.com.tp.lanchescaieiras._external.integrations.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;

public interface KitchenOrderIntegration {

    void cancelKitchenOrderById(Integer kitchenOrderOrderId);

    void createKitchenOrder(CustomerOrderDTO customerOrderDTO);

    KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer id);

    void updateKitchenOrderById(Integer kitchenOrderOrderId, String newStatus, Boolean forceUpdate);
}
