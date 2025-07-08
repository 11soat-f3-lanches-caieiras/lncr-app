package br.com.tp.lanchescaieiras._core.applications.customerorder.usecases;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.CustomerOrderUseCaseUtils;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderStatus;

public class CreateCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public CreateCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder execute(CustomerOrderDTO customerOrderDTO) {
        customerOrderDTO.setStatus(CustomerOrderStatus.CHECKOUT.getDescription());
        CustomerOrder customerOrder = new CustomerOrder(customerOrderDTO);
        //Obtendo informações do cliente
        CustomerOrderUseCaseUtils.getCustomerDetails(customerOrder,customerOrderGateway);
        //Obtendo informações dos items de alimentação
        CustomerOrderUseCaseUtils.getFoodItemsDetails(customerOrder, customerOrderGateway);
        customerOrder = this.customerOrderGateway.createCustomerOrder(customerOrder);

        //TODO - implementar integrações com pagamento e notificações
        //this.customerOrderGateway.createPaymentCharge(customerOrder);
        //this.customerOrderGateway.sendNotification("CustomerOrder",1,"mensagem");

        return customerOrder;
    }


}
