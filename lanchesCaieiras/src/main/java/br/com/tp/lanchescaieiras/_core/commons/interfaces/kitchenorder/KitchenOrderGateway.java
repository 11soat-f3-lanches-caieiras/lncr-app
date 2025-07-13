package br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder;

import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;

public interface KitchenOrderGateway {

    KitchenOrder save(KitchenOrder kitchenOrder);

    KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId);
}
