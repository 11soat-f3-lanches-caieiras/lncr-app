package br.com.tp.lanchescaieiras._core.applications.customerorder;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.CustomerOrderUseCaseUtils;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderStatus;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

import java.util.ArrayList;
import java.util.List;

public class GetCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public GetCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder getById(Integer customerOrderId, Boolean includFoodItems) {
        CustomerOrder customerOrder = this.customerOrderGateway.getCustomerOrderById(customerOrderId,includFoodItems);
        if (customerOrder == null) {
            throw new CustomerOrderException("Não encontrado pedido com id: " + customerOrderId,404);
        }
        CustomerOrderUseCaseUtils.getCustomerDetails(customerOrder,customerOrderGateway);
        if (includFoodItems == true) CustomerOrderUseCaseUtils.getFoodItemsDetails(customerOrder, customerOrderGateway);
        return customerOrder;
    }

    public List<CustomerOrder>  getByStatusList(List<String> statusList, Boolean includeFoodItems) {
        List<Integer> statusListIds = getStatusListIds(statusList);
        List<CustomerOrder> customerOrderList = this.customerOrderGateway.getCustomerOrderByStatusList(statusListIds, includeFoodItems);
        if (customerOrderList == null || customerOrderList.isEmpty()){
            throw new CustomerOrderException("Não existe pedidos com os status: " + String.join(", ", statusList) ,404);
        }
        CustomerOrderUseCaseUtils.getCustomerDetailsList(customerOrderList, customerOrderGateway);
        if (includeFoodItems == true) CustomerOrderUseCaseUtils.getFoodItemsDetailsList(customerOrderList,customerOrderGateway);
        return customerOrderList;
    }
    
    private List<Integer> getStatusListIds(List<String> statusList){
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(CustomerOrderStatus.fromDescription(status).getId());
        }
        return statusListIds;
    }
}
