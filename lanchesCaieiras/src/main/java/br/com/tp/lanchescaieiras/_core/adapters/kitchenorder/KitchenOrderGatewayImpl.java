package br.com.tp.lanchescaieiras._core.adapters.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenSort;

import java.util.List;

public class KitchenOrderGatewayImpl implements KitchenOrderGateway {

    private final KitchenOrderDatabase kitchenOrderDatabase;
    private final KitchenOrderMapper kitchenOrderMapper;

    public KitchenOrderGatewayImpl(KitchenOrderDatabase kitchenOrderDatabase, KitchenOrderMapper kitchenOrderMapper) {
        this.kitchenOrderDatabase = kitchenOrderDatabase;
        this.kitchenOrderMapper = kitchenOrderMapper;
    }

    @Override
    public KitchenSort save(KitchenSort kitchenOrder) {
        KitchenOrderDTO kitchenOrderDTO = kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
        kitchenOrderDTO = this.kitchenOrderDatabase.save(kitchenOrderDTO);
        return kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }

    @Override
    public KitchenSort getKitchenOrderByCustomerOrderId(Integer customerOrderId) {
        return getKitchenOrderByCustomerOrderId(customerOrderId,false);
    }

    @Override
    public KitchenSort getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems) {
        KitchenOrderDTO kitchenOrderDTO = this.kitchenOrderDatabase.findByCustomerOrderId(customerOrderId,includeFoodItems);
        return this.kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }

    @Override
    public List<KitchenSort> getKitchenOrderByStatusList(List<Integer> statusList, Boolean includeFoodItems) {
        List<KitchenOrderDTO> kitchenOrderDTOList = this.kitchenOrderDatabase.findByStatusList(statusList, includeFoodItems);
        return kitchenOrderDTOList.stream()
                .map(this.kitchenOrderMapper::kichenOrderToDomain)
                .toList();
    }

    @Override
    public KitchenSort getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems) {
        KitchenOrderDTO kitchenOrderDTO = this.kitchenOrderDatabase.findById(kitchenOrderId,includeFoodItems);
        return this.kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }
}
