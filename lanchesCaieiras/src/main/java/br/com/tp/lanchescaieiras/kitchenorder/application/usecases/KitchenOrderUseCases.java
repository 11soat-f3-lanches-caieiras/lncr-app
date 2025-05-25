package br.com.tp.lanchescaieiras.kitchenorder.application.usecases;

import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;

import java.util.List;

public interface KitchenOrderUseCases {
    public KitchenOrder createKitchenOrder(KitchenOrder kitchenOrder);

    public KitchenOrder findById(Integer id, Boolean includeFoodItems) ;

    KitchenOrder getKitchenOrderByCustomerOrderById(Integer customerOrderId, Boolean includeFoodItems);

    public List<KitchenOrder> findByStatus(String status, Boolean includeFoodItems);

    KitchenOrder updateStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder);
}
