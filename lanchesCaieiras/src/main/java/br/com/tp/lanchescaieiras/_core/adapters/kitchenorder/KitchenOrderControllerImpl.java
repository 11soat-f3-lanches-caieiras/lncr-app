package br.com.tp.lanchescaieiras._core.adapters.kitchenorder;

import br.com.tp.lanchescaieiras._core.applications.kitchenorder.CreateKitchenOrderUseCase;
import br.com.tp.lanchescaieiras._core.applications.kitchenorder.GetKitchenOrderUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenSort;

import java.util.List;

public class KitchenOrderControllerImpl implements KitchenOrderController {

    private final KitchenOrderMapper kitchenOrderMapper;
    private final KitchenOrderGateway kitchenOrderGateway;

    public KitchenOrderControllerImpl(KitchenOrderDatabase kitchenOrderDatabase) {
        this.kitchenOrderMapper = new KitchenOrderMapper();
        this.kitchenOrderGateway = new KitchenOrderGatewayImpl(kitchenOrderDatabase, kitchenOrderMapper);
    }

    @Override
    public KitchenOrderDTO createKitchenOrder(KitchenOrderDTO kitchenOrderDTO) {
        KitchenSort kitchenOrder = new CreateKitchenOrderUseCase(kitchenOrderGateway).execute(kitchenOrderDTO);
        return new KitchenOrderPresenter(kitchenOrderMapper).created(kitchenOrder);
    }

    @Override
    public KitchenOrderDTO getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems) {
        KitchenSort kitchenOrder = new GetKitchenOrderUseCase(kitchenOrderGateway).getById(kitchenOrderId, includeFoodItems);
        return new KitchenOrderPresenter(kitchenOrderMapper).getById(kitchenOrder);
    }

    @Override
    public KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems) {
        KitchenSort kitchenOrder = new GetKitchenOrderUseCase(kitchenOrderGateway).getByCustomerOrderId(customerOrderId, includeFoodItems);
        return new KitchenOrderPresenter(kitchenOrderMapper).getByCustomerOrderId(kitchenOrder);
    }

    public List<KitchenOrderDTO> getKitchenOrderByStatusList(List<String> statusList, Boolean includeFoodItems) {
        List<KitchenSort> kitchenOrderList = new GetKitchenOrderUseCase(kitchenOrderGateway).getByStatusList(statusList,includeFoodItems);
        return new KitchenOrderPresenter(kitchenOrderMapper).getByStatusList(kitchenOrderList,statusList );
    }
}
