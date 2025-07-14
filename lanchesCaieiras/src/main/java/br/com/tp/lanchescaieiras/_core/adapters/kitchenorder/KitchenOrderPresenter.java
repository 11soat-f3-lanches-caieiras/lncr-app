package br.com.tp.lanchescaieiras._core.adapters.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.StatusOrderUtils;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenSort;

import java.util.List;

public class KitchenOrderPresenter {

    private final KitchenOrderMapper kitchenOrderMapper;

    public KitchenOrderPresenter(KitchenOrderMapper kitchenOrderMapper) {
        this.kitchenOrderMapper = kitchenOrderMapper;
    }

    public KitchenOrderDTO created(KitchenSort kitchenOrder) {
        clearKitchenOrderIdInFoodItem(kitchenOrder);
        return kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
    }

    public KitchenOrderDTO getById(KitchenSort kitchenOrder) {
        clearKitchenOrderIdInFoodItem(kitchenOrder);
        return kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
    }

    public KitchenOrderDTO getByCustomerOrderId(KitchenSort kitchenOrder) {
        clearKitchenOrderIdInFoodItem(kitchenOrder);
        return kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
    }

    public List<KitchenOrderDTO> getByStatusList(List<KitchenSort> kitchenOrderList, List<String> statusList) {
        kitchenOrderList.forEach(this::clearKitchenOrderIdInFoodItem);
        return StatusOrderUtils.sortByStatusOrder(kitchenOrderList, statusList)
                .stream()
                .map(kitchenOrderMapper::kitchenOrderToDTO).toList();
    }

    private void clearKitchenOrderIdInFoodItem(KitchenSort kitchenOrder) {
        if (kitchenOrder.getFoodItems() != null && !kitchenOrder.getFoodItems().isEmpty()) {
            kitchenOrder.getFoodItems().forEach(foodItem -> {
                foodItem.setKitchenOrderId(null);
            });
        }
    }
}

