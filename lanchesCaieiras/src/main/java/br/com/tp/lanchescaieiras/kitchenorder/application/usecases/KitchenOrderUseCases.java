package br.com.tp.lanchescaieiras.kitchenorder.application.usecases;

import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;

import java.util.List;

public interface KitchenOrderUseCases {
    KitchenOrder createKitchenOrder(KitchenOrder kitchenOrder);

    KitchenOrder findById(Integer id, Boolean includeFoodItems);

    KitchenOrder getKitchenOrderByCustomerOrderById(Integer customerOrderId, Boolean includeFoodItems);

    List<KitchenOrder> findByStatus(String status, Boolean includeFoodItems);

    KitchenOrder updateStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder);
}
