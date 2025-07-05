package br.com.tp.lanchescaieiras._core.domain.kitchenorder;

import java.util.List;

public interface KitchenOrderFoodItemRepository {

    KitchenOrderFoodItem save(KitchenOrderFoodItem foodItem, Integer kitchenOrderId);

    List<KitchenOrderFoodItem> findByKitchenOrderId(Integer kitchenOrderId);

}
