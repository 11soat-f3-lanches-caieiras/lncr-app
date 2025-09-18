package br.com.tp.lncr.app.integrations.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;

public interface KitchenOrderIntegration {

    void cancelKitchenOrderById(Integer kitchenOrderOrderId);

    void createKitchenOrder(CustomerOrderDTO customerOrderDTO);

    KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer id);

    void updateKitchenOrderById(Integer kitchenOrderOrderId, String newStatus, Boolean forceUpdate);
}
