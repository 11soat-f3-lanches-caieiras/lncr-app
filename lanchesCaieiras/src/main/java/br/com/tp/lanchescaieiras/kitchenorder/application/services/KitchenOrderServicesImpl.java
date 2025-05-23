package br.com.tp.lanchescaieiras.kitchenorder.application.services;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.integrations.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.repositories.JpaKitchenOrderFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.repositories.JpaKitchenOrderRepositoryImpl;
import br.com.tp.lanchescaieiras.kitchenorder.application.usecases.KitchenOrderUseCases;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderStatus;
import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.exceptions.KitchenOrderException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class KitchenOrderServicesImpl implements KitchenOrderUseCases {

    private final JpaKitchenOrderRepositoryImpl jpaKitchenOrderRepositoryImpl;
    private final JpaKitchenOrderFoodItemRepositoryImpl jpaKitchenOrderFoodItemRepository;
    private final FoodItemIntegrationImpl foodItemIntegration;

    public KitchenOrderServicesImpl(JpaKitchenOrderRepositoryImpl jpaKitchenOrderRepositoryImpl,
                                    JpaKitchenOrderFoodItemRepositoryImpl jpaKitchenOrderFoodItemRepository,
                                    FoodItemIntegrationImpl foodItemIntegration){
            this.jpaKitchenOrderRepositoryImpl = jpaKitchenOrderRepositoryImpl;
            this.jpaKitchenOrderFoodItemRepository = jpaKitchenOrderFoodItemRepository;
            this.foodItemIntegration = foodItemIntegration;
        }

    @Override
    public KitchenOrder createKitchenOrder(KitchenOrder kitchenOrder) {
        kitchenOrder = kitchenOrderFoodsItemDetails(kitchenOrder);

        KitchenOrder createdKitchenOrder = jpaKitchenOrderRepositoryImpl.save(kitchenOrder);
        createdKitchenOrder.setFoodItems(new ArrayList<>());

        for (KitchenOrderFoodItem kitchenOrderFoodItem : kitchenOrder.getFoodItems()) {
            kitchenOrderFoodItem = jpaKitchenOrderFoodItemRepository.save(kitchenOrderFoodItem, createdKitchenOrder.getId());
            createdKitchenOrder.getFoodItems().add(kitchenOrderFoodItem);
        }
        return createdKitchenOrder;
    }

    @Override
    public KitchenOrder findById(Integer id, Boolean includeFoodItems) {
        KitchenOrder kitchenOrder = jpaKitchenOrderRepositoryImpl.findById(id);
        kitchenOrder = includeFoodItemsDetails(kitchenOrder, includeFoodItems);
        return kitchenOrder;
    }

    @Override
    public List<KitchenOrder> findByStatus(String status, Boolean includeFoodItems) {
        Integer statusId = KitchenOrderStatus.fromDescription(status).getId();
        List<KitchenOrder> kitchenOrders = jpaKitchenOrderRepositoryImpl.findByStatusId(statusId);
        for(KitchenOrder kitchenOrder : kitchenOrders) {
            kitchenOrder = includeFoodItemsDetails(kitchenOrder, includeFoodItems);
        }
        return kitchenOrders;
    }

    @Override
    public KitchenOrder updateStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate) {
        KitchenOrder kitchenOrder = jpaKitchenOrderRepositoryImpl.findById(kitchenOrderId);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado pedido com id: " + kitchenOrderId,404);
        }
        if (forceUpdate = false) {
            validateNewStatus(kitchenOrder.getStatus(), newStatus);
        }
        kitchenOrder.setStatus(KitchenOrderStatus.fromDescription(newStatus).getDescription());
        kitchenOrder = jpaKitchenOrderRepositoryImpl.save(kitchenOrder);
        return kitchenOrder;
    }

    public void validateNewStatus(String actualStatus, String newStatus) {
        Integer actualStatusId = KitchenOrderStatus.fromDescription(actualStatus).getId();
        Integer newStatusId = KitchenOrderStatus.fromDescription(newStatus).getId();
        if (actualStatusId + 1 != newStatusId) {
            throw new KitchenOrderException("Erro na atualização no status do pedido. Não é permitido atualizar o status de: " + actualStatus + " para: " + newStatus,400);
        }
    }

    private KitchenOrder includeFoodItemsDetails(KitchenOrder kitchenOrder, Boolean includeFoodItems) {
        if (includeFoodItems) {
            kitchenOrder.setFoodItems(jpaKitchenOrderFoodItemRepository.findByKitchenOrderId(kitchenOrder.getId()));
            kitchenOrder = kitchenOrderFoodsItemDetails(kitchenOrder);
        }
        return kitchenOrder;
    }


    private KitchenOrder kitchenOrderFoodsItemDetails(KitchenOrder kitchenOrder){
        List<Integer> foodItemIds = kitchenOrder.getFoodItems().stream().map(KitchenOrderFoodItem::getId)
                .collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
        List<KitchenOrderFoodItem> kitchenOrderFoodItemsDetails = getFoodItemsDetailsIntegration(foodItemIds);
        kitchenOrder.setFoodItems(mergeFoodItemDetails(kitchenOrder.getFoodItems(), kitchenOrderFoodItemsDetails));
        return kitchenOrder;
    }

    private List<KitchenOrderFoodItem> getFoodItemsDetailsIntegration(List<Integer> foodItemIds) {
        List<KitchenOrderFoodItem> kitchenOrderFoodItemsDetails = new ArrayList<>();
        for (Integer foodItemId : foodItemIds) {
            kitchenOrderFoodItemsDetails.add(foodItemIntegration.getFoodItemsDetails(foodItemId));
        }
        return kitchenOrderFoodItemsDetails;
    }

    private List<KitchenOrderFoodItem> mergeFoodItemDetails(List<KitchenOrderFoodItem> foodItems,
                                                             List<KitchenOrderFoodItem> kitchenOrderFoodItemsDetails) {
        for (KitchenOrderFoodItem kitchenOrderFoodItem : foodItems) {
            for (KitchenOrderFoodItem kitchenOrderFoodItemDetails : kitchenOrderFoodItemsDetails) {
                if (kitchenOrderFoodItem.getId() == kitchenOrderFoodItemDetails.getId()) {
                    kitchenOrderFoodItem.setName(kitchenOrderFoodItemDetails.getName());
                    kitchenOrderFoodItem.setDescription(kitchenOrderFoodItemDetails.getDescription());
                    }
                }
            }
        return foodItems;
        }
}



