package br.com.tp.lanchescaieiras._core.adapters.kitchenorder;

import br.com.tp.lanchescaieiras._core.applications.kitchenorder.usecases.CreateKitchenOrderUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;

public class KitchenOrderControllerImpl implements KitchenOrderController {

    private final KitchenOrderMapper kitchenOrderMapper;
    private final KitchenOrderGateway kitchenOrderGateway;

    public KitchenOrderControllerImpl(KitchenOrderDatabase kitchenOrderDatabase) {
        this.kitchenOrderMapper = new KitchenOrderMapper();
        this.kitchenOrderGateway = new KitchenOrderGatewayImpl(kitchenOrderDatabase, kitchenOrderMapper);
    }


    @Override
    public KitchenOrderDTO createKitchenOrder(KitchenOrderDTO kitchenOrderDTO) {
        KitchenOrder kitchenOrder = new CreateKitchenOrderUseCase(kitchenOrderGateway).execute(kitchenOrderDTO);
        return new KitchenOrderPresenter(kitchenOrderMapper).created(kitchenOrder);
    }
}
