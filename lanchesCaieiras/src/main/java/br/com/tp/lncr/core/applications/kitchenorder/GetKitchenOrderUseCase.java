package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.commons.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.commons.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;

import java.util.ArrayList;
import java.util.List;

public class GetKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public GetKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder getById(Integer kitchenOrderId, Boolean includeFoodItems) {
        Logger.info("Iniciando busca de preparo pelo id: " + kitchenOrderId);
        KitchenOrder kitchenOrder = this.kitchenOrderGateway.getKitchenOrderById(kitchenOrderId, includeFoodItems);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado preparo para o id: " + kitchenOrderId,404);
        }
        Logger.info("Preparo encontrado com sucesso, id: " + kitchenOrderId);
        return kitchenOrder;
    }

    public KitchenOrder getByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems) {
        Logger.info("Iniciando busca de preparo pelo id do pedido do cliente: " + customerOrderId);
        KitchenOrder kitchenOrder = this.kitchenOrderGateway.getKitchenOrderByCustomerOrderId(customerOrderId, includeFoodItems);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado preparo para o id: " + customerOrderId,404);
        }
        Logger.info("Preparo encontrado com sucesso, id do pedido do cliente: " + customerOrderId);
        return kitchenOrder;
    }

    public List<KitchenOrder> getByStatusList(List<String> statusList, Boolean includeFoodItems) {
        Logger.info("Iniciando busca de preparos pelos status: " + statusList);
        List<KitchenOrder> kitchenOrderList = this.kitchenOrderGateway.getKitchenOrderByStatusList(getStatusListIds(statusList), includeFoodItems);
        if (kitchenOrderList == null || kitchenOrderList.isEmpty()) {
            throw new KitchenOrderException("Não encontrado preparo para os status: " + statusList, 404);
        }
        Logger.info("Preparos encontrados com sucesso, status: " + statusList);
        return kitchenOrderList;
    }

    private List<Integer> getStatusListIds(List<String> statusList){
        Logger.debug("Convertendo lista de status para IDs: " + statusList);
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(KitchenOrderStatus.fromDescription(status).getId());
        }
        Logger.debug("Lista de IDs de status convertida: " + statusListIds);
        return statusListIds;
    }
}
