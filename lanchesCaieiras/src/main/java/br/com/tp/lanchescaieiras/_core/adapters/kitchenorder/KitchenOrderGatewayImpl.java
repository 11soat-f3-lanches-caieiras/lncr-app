package br.com.tp.lanchescaieiras._core.adapters.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;

public class KitchenOrderGatewayImpl implements KitchenOrderGateway {

    private final KitchenOrderDatabase kitchenOrderDatabase;
    private final KitchenOrderMapper kitchenOrderMapper;

    public KitchenOrderGatewayImpl(KitchenOrderDatabase kitchenOrderDatabase, KitchenOrderMapper kitchenOrderMapper) {
        this.kitchenOrderDatabase = kitchenOrderDatabase;
        this.kitchenOrderMapper = kitchenOrderMapper;
    }

    @Override
    public KitchenOrder save(KitchenOrder kitchenOrder) {
        KitchenOrderDTO kitchenOrderDTO = kitchenOrderMapper.kitchenOrderToDTO(kitchenOrder);
        kitchenOrderDTO = this.kitchenOrderDatabase.save(kitchenOrderDTO);
        return kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }

    @Override
    public KitchenOrder getKitchenOrderByCustomerOrderId(Integer customerOrderId) {
        KitchenOrderDTO kitchenOrderDTO = this.kitchenOrderDatabase.findByCustomerOrderId(customerOrderId);
        return this.kitchenOrderMapper.kichenOrderToDomain(kitchenOrderDTO);
    }
}
