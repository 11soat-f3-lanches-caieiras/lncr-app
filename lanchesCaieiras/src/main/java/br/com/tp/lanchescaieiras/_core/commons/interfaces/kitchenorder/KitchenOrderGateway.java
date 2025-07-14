package br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder;

import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;

import java.util.List;

public interface KitchenOrderGateway {

    KitchenOrder save(KitchenOrder kitchenOrder);

    KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId);

    KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);

    List<KitchenOrder> getKitchenOrderByStatusList(List<Integer> statusList, Boolean includeFoodItems);

    KitchenOrder getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems);

    KitchenOrder getKitchenOrderById(Integer kitchenOrderId);

    void updateCustomerOrderStatus(Integer customerOrderId, String status);

    void sendNotification(String notificationType, Integer artefactId, String message);
}
