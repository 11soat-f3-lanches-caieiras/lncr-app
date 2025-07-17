package br.com.tp.lanchescaieiras._core.adapters.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;

import java.util.List;

public class KitchenOrderGatewayImpl implements KitchenOrderGateway {

    private final KitchenOrderDatabase kitchenOrderDatabase;
    private final KitchenOrderMapper kitchenOrderMapper;

    public KitchenOrderGatewayImpl(KitchenOrderDatabase kitchenOrderDatabase, KitchenOrderMapper kitchenOrderMapper) {
        this.kitchenOrderDatabase = kitchenOrderDatabase;
        this.kitchenOrderMapper = kitchenOrderMapper;
    }

    @Override
    public KitchenOrder saveKitchenOrder(KitchenOrder kitchenOrder) {
        KitchenOrderDTO kitchenOrderDTO = kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
        kitchenOrderDTO = this.kitchenOrderDatabase.save(kitchenOrderDTO);
        return kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }

    @Override
    public KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId) {
        return getKitchenOrderByCustomerOrderId(customerOrderId,false);
    }

    @Override
    public KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems) {
        KitchenOrderDTO kitchenOrderDTO = this.kitchenOrderDatabase.findByCustomerOrderId(customerOrderId,includeFoodItems);
        return this.kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }

    @Override
    public List<KitchenOrder> getKitchenOrderByStatusList(List<Integer> statusList, Boolean includeFoodItems) {
        List<KitchenOrderDTO> kitchenOrderDTOList = this.kitchenOrderDatabase.findByStatusList(statusList, includeFoodItems);
        return kitchenOrderDTOList.stream()
                .map(this.kitchenOrderMapper::kichenOrderToDomain)
                .toList();
    }

    @Override
    public KitchenOrder getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems) {
        KitchenOrderDTO kitchenOrderDTO = this.kitchenOrderDatabase.findById(kitchenOrderId,includeFoodItems);
        return this.kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }

    @Override
    public KitchenOrder getKitchenOrderById(Integer kitchenOrderId) {
        return getKitchenOrderById(kitchenOrderId, false);
    }

    @Override
    public void updateCustomerOrderStatus(Integer customerOrderId, String status) {
        this.kitchenOrderDatabase.updateCustomerOrderStatus(customerOrderId, status);
    }

    @Override
    public void sendNotification(String notificationType, Integer artefactId, String message) {
        this.kitchenOrderDatabase.sendNotification(notificationType, artefactId, message);
    }
}
