package br.com.tp.lanchescaieiras._core.applications.customerorder;

import br.com.tp.lanchescaieiras._core.commons.enums.CustomerOrderStatus;
import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerOrderException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;

import java.util.ArrayList;
import java.util.List;

public class GetCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public GetCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder getById(Integer customerOrderId, Boolean includFoodItems) {
        Logger.info("GetCustomerOrderUseCase.getById - Iniciando busca por pedido com id: " + customerOrderId);
        CustomerOrder customerOrder = this.customerOrderGateway.getCustomerOrderById(customerOrderId,includFoodItems);
        if (customerOrder == null) {
            throw new CustomerOrderException("Não encontrado pedido com id: " + customerOrderId,404);
        }
        CustomerOrderUseCaseUtils.getCustomerDetails(customerOrder,customerOrderGateway);
        if (includFoodItems == true) CustomerOrderUseCaseUtils.getFoodItemsDetails(customerOrder, customerOrderGateway);
        Logger.info("GetCustomerOrderUseCase.getById - Pedido encontrado: " + customerOrder.toString());
        return customerOrder;
    }

    public List<CustomerOrder>  getByStatusList(List<String> statusList, Boolean includeFoodItems) {
        Logger.info("GetCustomerOrderUseCase.getByStatusList - Iniciando busca por pedidos com status: " + String.join(", ", statusList));
        List<Integer> statusListIds = getStatusListIds(statusList);
        List<CustomerOrder> customerOrderList = this.customerOrderGateway.getCustomerOrderByStatusList(statusListIds, includeFoodItems);
        if (customerOrderList == null || customerOrderList.isEmpty()){
            throw new CustomerOrderException("Não existe pedidos com os status: " + String.join(", ", statusList) ,404);
        }
        CustomerOrderUseCaseUtils.getCustomerDetailsList(customerOrderList, customerOrderGateway);
        if (includeFoodItems == true) CustomerOrderUseCaseUtils.getFoodItemsDetailsList(customerOrderList,customerOrderGateway);
        Logger.info("GetCustomerOrderUseCase.getByStatusList - Pedidos encontrados: " + customerOrderList.size());
        return customerOrderList;
    }
    
    private List<Integer> getStatusListIds(List<String> statusList){
        Logger.debug("GetCustomerOrderUseCase.getStatusListIds - Convertendo status de pedidos para IDs");
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(CustomerOrderStatus.fromDescription(status).getId());
        }
        return statusListIds;
    }
}
