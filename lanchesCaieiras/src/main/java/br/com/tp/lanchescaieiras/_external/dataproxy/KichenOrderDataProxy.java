package br.com.tp.lanchescaieiras._external.dataproxy;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderFoodItem;
import br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder.*;
import br.com.tp.lanchescaieiras._external.integrations.customerorder.CustomerOrderIntegrationImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KichenOrderDataProxy implements KitchenOrderDatabase {

    private final JpaKitchenOrderRepositoryImpl jpaKitchenOrderRepositoryImpl;
    private final JpaKitchenOrderRepository jpaKitchenOrderRepository;
    private final JpaKitchenOrderFoodItemRepositoryImpl jpaKitchenOrderFoodItemRepositoryImpl;
    private final JpaKitchenOrderFoodItemRepository jpaKitchenOrderFoodItemRepository;
    private final CustomerOrderIntegrationImpl customerOrderIntegrationImpl;
    private final JpaKitchenOrderMapper jpaKitchenOrderMapper;

    public KichenOrderDataProxy(JpaKitchenOrderRepositoryImpl jpaKitchenOrderRepositoryImpl,
                                JpaKitchenOrderRepository jpaKitchenOrderRepository,
                                JpaKitchenOrderFoodItemRepositoryImpl jpaKitchenOrderFoodItemRepositoryImpl,
                                JpaKitchenOrderFoodItemRepository jpaKitchenOrderFoodItemRepository,
                                CustomerOrderIntegrationImpl customerOrderIntegrationImpl,
                                JpaKitchenOrderMapper jpaKitchenOrderMapper) {
        this.jpaKitchenOrderRepositoryImpl = jpaKitchenOrderRepositoryImpl;
        this.jpaKitchenOrderRepository = jpaKitchenOrderRepository;
        this.jpaKitchenOrderFoodItemRepositoryImpl = jpaKitchenOrderFoodItemRepositoryImpl;
        this.jpaKitchenOrderFoodItemRepository = jpaKitchenOrderFoodItemRepository;
        this.customerOrderIntegrationImpl = customerOrderIntegrationImpl;
        this.jpaKitchenOrderMapper = jpaKitchenOrderMapper;
    }

    @Override
    public KitchenOrderDTO save(KitchenOrderDTO kitchenOrderDto) {
        kitchenOrderDto = this.jpaKitchenOrderRepositoryImpl.save(kitchenOrderDto, jpaKitchenOrderRepository, jpaKitchenOrderMapper);
        Integer kitchenOrderId = kitchenOrderDto.getId();
        setKitchenOrderIdOnFoodItems(kitchenOrderDto);
        kitchenOrderDto.setFoodItems(this.jpaKitchenOrderFoodItemRepositoryImpl.saveAll(kitchenOrderDto.getFoodItems(),jpaKitchenOrderFoodItemRepository,jpaKitchenOrderMapper));
        return kitchenOrderDto;
    }

    @Override
    public KitchenOrderDTO findById(Integer kitchenOrderId) {
        return null;
    }

    @Override
    public List<KitchenOrderDTO> findByStatusId(Integer statusId) {
        return List.of();
    }

    @Override
    public KitchenOrderDTO updateStatusByCustomerOrderId(Integer customerOrderId, String newStatus) {
        return null;
    }

    @Override
    public KitchenOrderDTO findByCustomerOrderId(Integer customerOrderId) {
        return this.jpaKitchenOrderRepositoryImpl.findByCustomerOrderId(customerOrderId, jpaKitchenOrderRepository, jpaKitchenOrderMapper);
    }

    @Override
    public List<KitchenOrderFoodItemDTO> saveAll(List<KitchenOrderFoodItem> kitchenOrderFoodItemList) {
        return List.of();
    }

    @Override
    public List<KitchenOrderFoodItemDTO> findByKitchenOrderId(Integer kitchenOrderId) {
        return List.of();
    }

    private void setKitchenOrderIdOnFoodItems(KitchenOrderDTO kitchenOrderDto) {
        Integer kitchenOrderId = kitchenOrderDto.getId();
        kitchenOrderDto.getFoodItems().forEach(
                foodItem -> foodItem.setKitchenOrderId(kitchenOrderId)
        );
    }
}
