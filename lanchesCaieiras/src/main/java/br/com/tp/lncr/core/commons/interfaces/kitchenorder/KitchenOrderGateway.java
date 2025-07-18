package br.com.tp.lncr.core.commons.interfaces.kitchenorder;

import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;

import java.util.List;

public interface KitchenOrderGateway {

    KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId);

    KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);

    KitchenOrder getKitchenOrderById(Integer kitchenOrderId);

    KitchenOrder getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems);

    List<KitchenOrder> getKitchenOrderByStatusList(List<Integer> statusList, Boolean includeFoodItems);

    void sendNotification(String notificationType, Integer artefactId, String message);

    KitchenOrder saveKitchenOrder(KitchenOrder kitchenOrder);

    void updateCustomerOrderStatus(Integer customerOrderId, String status);
}
