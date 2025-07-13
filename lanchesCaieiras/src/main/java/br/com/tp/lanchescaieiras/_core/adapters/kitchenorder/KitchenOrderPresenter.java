package br.com.tp.lanchescaieiras._core.adapters.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;

public class KitchenOrderPresenter {

    private final KitchenOrderMapper kitchenOrderMapper;

    public KitchenOrderPresenter(KitchenOrderMapper kitchenOrderMapper) {
        this.kitchenOrderMapper = kitchenOrderMapper;
    }

    public KitchenOrderDTO created(KitchenOrder kitchenOrder) {
        clearKitchenOrderIdInFoodItem(kitchenOrder);
        return kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
    }

    private void clearKitchenOrderIdInFoodItem(KitchenOrder kitchenOrder) {
        if (kitchenOrder.getFoodItems() != null && !kitchenOrder.getFoodItems().isEmpty()) {
        kitchenOrder.getFoodItems().stream().forEach(foodItem -> {
                foodItem.setKitchenOrderId(null);
            });
        }
    }
}
