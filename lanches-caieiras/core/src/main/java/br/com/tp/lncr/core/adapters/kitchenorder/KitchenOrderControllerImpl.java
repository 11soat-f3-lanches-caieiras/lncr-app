package br.com.tp.lncr.core.adapters.kitchenorder;

import br.com.tp.lncr.core.applications.kitchenorder.CreateKitchenOrderUseCase;
import br.com.tp.lncr.core.applications.kitchenorder.GetKitchenOrderUseCase;
import br.com.tp.lncr.core.applications.kitchenorder.UpdateKitchenOrderUseCase;
import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderController;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;

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
        KitchenOrder kitchenOrder = new CreateKitchenOrderUseCase(kitchenOrderGateway).execute(kitchenOrderDTO);
        return new KitchenOrderPresenter(kitchenOrderMapper).created(kitchenOrder);
    }

    @Override
    public KitchenOrderDTO getKitchenOrderById(Integer kitchenOrderId, Boolean includeFoodItems) {
        KitchenOrder kitchenOrder = new GetKitchenOrderUseCase(kitchenOrderGateway).getById(kitchenOrderId, includeFoodItems);
        return new KitchenOrderPresenter(kitchenOrderMapper).getById(kitchenOrder);
    }

    @Override
    public KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems) {
        KitchenOrder kitchenOrder = new GetKitchenOrderUseCase(kitchenOrderGateway).getByCustomerOrderId(customerOrderId, includeFoodItems);
        return new KitchenOrderPresenter(kitchenOrderMapper).getByCustomerOrderId(kitchenOrder);
    }

    @Override
    public KitchenOrderDTO updateOrderStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder) {
        KitchenOrder kitchenOrder = new UpdateKitchenOrderUseCase(kitchenOrderGateway).updateStatus(kitchenOrderId, newStatus, forceUpdate, updateCustomerOrder);
        return new KitchenOrderPresenter(kitchenOrderMapper).getByCustomerOrderId(kitchenOrder);
    }

    @Override
    public List<KitchenOrderDTO> getKitchenOrderByStatusList(List<String> statusList, Boolean includeFoodItems) {
        List<KitchenOrder> kitchenOrderList = new GetKitchenOrderUseCase(kitchenOrderGateway).getByStatusList(statusList,includeFoodItems);
        return new KitchenOrderPresenter(kitchenOrderMapper).getByStatusList(kitchenOrderList,statusList );
    }
}
