package br.com.tp.lncr.core.adapters.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.utils.StatusOrderUtils;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;

import java.util.List;

public class KitchenOrderPresenter {

    private final KitchenOrderMapper kitchenOrderMapper;

    public KitchenOrderPresenter(KitchenOrderMapper kitchenOrderMapper) {
        this.kitchenOrderMapper = kitchenOrderMapper;
    }

    public KitchenOrderDTO created(KitchenOrder kitchenOrder) {
        clearKitchenOrderIdInFoodItem(kitchenOrder);
        return kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
    }

    public KitchenOrderDTO getById(KitchenOrder kitchenOrder) {
        clearKitchenOrderIdInFoodItem(kitchenOrder);
        return kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
    }

    public KitchenOrderDTO getByCustomerOrderId(KitchenOrder kitchenOrder) {
        clearKitchenOrderIdInFoodItem(kitchenOrder);
        return kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
    }

    public List<KitchenOrderDTO> getByStatusList(List<KitchenOrder> kitchenOrderList, List<String> statusList) {
        kitchenOrderList.forEach(this::clearKitchenOrderIdInFoodItem);
        return StatusOrderUtils.sortByStatusOrder(kitchenOrderList, statusList)
                .stream()
                .map(kitchenOrderMapper::kitchenOrderToDTO).toList();
    }

    private void clearKitchenOrderIdInFoodItem(KitchenOrder kitchenOrder) {
        if (kitchenOrder.getFoodItems() != null && !kitchenOrder.getFoodItems().isEmpty()) {
            kitchenOrder.getFoodItems().forEach(foodItem -> {
                foodItem.setKitchenOrderId(null);
            });
        }
    }
}

