package br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder;

import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenSort;

import java.util.List;

public interface KitchenOrderGateway {

    KitchenSort save(KitchenSort kitchenOrder);

    KitchenSort getKitchenOrderByCustomerOrderId(Integer customerOrderId);

    KitchenSort getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);

    List<KitchenSort> getKitchenOrderByStatusList(List<Integer> statusList, Boolean includeFoodItems);

    KitchenSort getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems);


}
