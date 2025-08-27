package br.com.tp.lncr.core.applications.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.enums.CustomerOrderStatus;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;

public class CreateCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public CreateCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder execute(CustomerOrderDTO customerOrderDTO) {
        Logger.info("Iniciando criação de pedido: " + customerOrderDTO.getId());

        customerOrderDTO.setStatus(CustomerOrderStatus.CHECKOUT.getDescription());
        CustomerOrder customerOrder = new CustomerOrder(customerOrderDTO);

        Logger.debug("Obtendo informações do cliente");
        CustomerOrderUseCaseUtils.getCustomerDetails(customerOrder,customerOrderGateway);

        Logger.debug("Obtendo informações dos items de alimentação");
        CustomerOrderUseCaseUtils.getFoodItemsDetails(customerOrder, customerOrderGateway);

        customerOrder = this.customerOrderGateway.createCustomerOrder(customerOrder);
        this.customerOrderGateway.createPaymentCharge(customerOrder);
        this.customerOrderGateway.sendNotification("CUSTOMER_ORDER_CHECKOUT",customerOrder.getId(),"Novo pedido realizado com id: " + customerOrder.getId() +"Aguardando pagamento");

        Logger.info("Pedido criado com sucesso: " + customerOrder.getId());
        return customerOrder;
    }


}
