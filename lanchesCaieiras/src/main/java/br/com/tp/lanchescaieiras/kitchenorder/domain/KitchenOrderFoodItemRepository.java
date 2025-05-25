package br.com.tp.lanchescaieiras.kitchenorder.domain;

import java.util.List;

public interface KitchenOrderFoodItemRepository {

    KitchenOrderFoodItem save(KitchenOrderFoodItem foodItem, Integer kitchenOrderId);

    List<KitchenOrderFoodItem> findByKitchenOrderId(Integer kitchenOrderId);

}
