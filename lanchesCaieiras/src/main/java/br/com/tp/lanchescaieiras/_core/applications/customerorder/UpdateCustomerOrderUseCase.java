package br.com.tp.lanchescaieiras._core.applications.customerorder;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.CustomerOrderUseCaseUtils;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerSort;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderStatus;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

public class UpdateCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public UpdateCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerSort updateStatusById(Integer customerOrderId, String newStatus, Boolean forceUpdate) {
        CustomerSort updateCustomerOrder= this.customerOrderGateway.getCustomerOrderById(customerOrderId);

        if (updateCustomerOrder != null) {
            updateCustomerOrder.setStatus(newStatus, forceUpdate);
            updateCustomerOrder = this.customerOrderGateway.updateCustomerOrder(updateCustomerOrder);
            // Se o status for RECEIVED, cria uma KitchenOrder
            if (updateCustomerOrder.getStatus().equals(CustomerOrderStatus.RECEIVED.getDescription())) {
                updateCustomerOrder = this.customerOrderGateway.getCustomerOrderById(customerOrderId, true);
                CustomerOrderUseCaseUtils.getFoodItemsDetails(updateCustomerOrder, this.customerOrderGateway);
                this.customerOrderGateway.createKitchenOrder(updateCustomerOrder);
            }
            CustomerOrderUseCaseUtils.getCustomerDetails(updateCustomerOrder, this.customerOrderGateway);
            return updateCustomerOrder;
        }
        throw new CustomerOrderException("Não encontrado pedido com o id: " +customerOrderId,404);

    }
}
