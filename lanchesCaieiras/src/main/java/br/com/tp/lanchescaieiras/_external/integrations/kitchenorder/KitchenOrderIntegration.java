package br.com.tp.lanchescaieiras._external.integrations.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;

public interface KitchenOrderIntegration {

    void createKitchenOrder(CustomerOrderDTO customerOrderDTO);
}
