package br.com.tp.lanchescaieiras._core.applications.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.KitchenOrderException;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderStatus;

import java.util.ArrayList;
import java.util.List;

public class GetKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public GetKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder getById(Integer kitchenOrderId, Boolean includeFoodItems) {
        KitchenOrder kitchenOrder = this.kitchenOrderGateway.getKitchenOrderById(kitchenOrderId, includeFoodItems);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado preparo para o id: " + kitchenOrderId,404);
        }
        return kitchenOrder;
    }

    public KitchenOrder getByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems) {
        KitchenOrder kitchenOrder = this.kitchenOrderGateway.getKitchenOrderByCustomerOrderId(customerOrderId, includeFoodItems);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado preparo para o id: " + customerOrderId,404);
        }
        return kitchenOrder;
    }

    public List<KitchenOrder> getByStatusList(List<String> statusList, Boolean includeFoodItems) {
        List<KitchenOrder> kitchenOrderList = this.kitchenOrderGateway.getKitchenOrderByStatusList(getStatusListIds(statusList), includeFoodItems);
        if (kitchenOrderList == null || kitchenOrderList.isEmpty()) {
            throw new KitchenOrderException("Não encontrado preparo para os status: " + statusList, 404);
        }
        return kitchenOrderList;
    }

    private List<Integer> getStatusListIds(List<String> statusList){
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(KitchenOrderStatus.fromDescription(status).getId());
        }
        return statusListIds;
    }
}
