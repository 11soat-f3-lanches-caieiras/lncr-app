package br.com.tp.lanchescaieiras.kitchenorder.domain;

import java.util.List;

public interface KitchenOrderFoodItemRepository {

    KitchenOrderFoodItem save(KitchenOrderFoodItem foodItem, Integer id);

    List<KitchenOrderFoodItem> findByKitchenOrderId(Integer kitchenOrderId);

}
