package br.com.tp.lanchescaieiras._core.applications.customerorder.usecases;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.CustomerOrderUseCaseUtils;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

public class UpdateCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public UpdateCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder updateStatusById(Integer customerOrderId, String newStatus, Boolean forceUpdate) {
        CustomerOrder updateCustomerOrder= this.customerOrderGateway.getCustomerOrderById(customerOrderId);

        if (updateCustomerOrder == null) {
            throw new CustomerOrderException("Não encontrado pedido com o id: " +customerOrderId,404);
        }
        CustomerOrderUseCaseUtils.getCustomerDetails(updateCustomerOrder,this.customerOrderGateway);
        CustomerOrderUseCaseUtils.getFoodItemsDetails(updateCustomerOrder,this.customerOrderGateway);
        updateCustomerOrder.setStatus(newStatus,forceUpdate);
        updateCustomerOrder = this.customerOrderGateway.updateCustomerOrder(updateCustomerOrder);

        return updateCustomerOrder;
    }
}
