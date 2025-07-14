package br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderFoodItem;

import java.util.List;

public interface KitchenOrderDatabase {

    KitchenOrderDTO save(KitchenOrderDTO kitchenOrderDto);

    KitchenOrderDTO findById(Integer kitchenOrderId, Boolean includeFoodItems);

    KitchenOrderDTO findById(Integer kitchenOrderId);

    KitchenOrderDTO findByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems);

    List<KitchenOrderDTO> findByStatusList(List<Integer> statusIdsList, Boolean includeFoodItems);

    List<KitchenOrderFoodItemDTO> findByKitchenOrderId(Integer kitchenOrderId);


    List<KitchenOrderDTO> findByStatusId(Integer statusId);

    KitchenOrderDTO updateStatusByCustomerOrderId(Integer customerOrderId, String newStatus);


    List<KitchenOrderFoodItemDTO> saveAll(List<KitchenOrderFoodItem> kitchenOrderFoodItemList);



    List<KitchenOrderFoodItemDTO> getFoodItemsByKitchenOrderId(Integer kitchenOrderId);


}
