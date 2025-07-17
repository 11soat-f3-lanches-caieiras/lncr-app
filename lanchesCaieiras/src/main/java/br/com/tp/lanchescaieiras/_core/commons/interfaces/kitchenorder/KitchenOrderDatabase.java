package br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;

import java.util.List;

public interface KitchenOrderDatabase {

    KitchenOrderDTO findByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);

    KitchenOrderDTO findById(Integer kitchenOrderId);

    KitchenOrderDTO findById(Integer kitchenOrderId, Boolean includeFoodItems);

    List<KitchenOrderFoodItemDTO> findByKitchenOrderId(Integer kitchenOrderId);

    List<KitchenOrderDTO> findByStatusList(List<Integer> statusIdsList, Boolean includeFoodItems);

    void sendNotification(String notificationType, Integer artefactId, String message);

    KitchenOrderDTO save(KitchenOrderDTO kitchenOrderDto);

    void updateCustomerOrderStatus(Integer customerOrderId, String status);
}
