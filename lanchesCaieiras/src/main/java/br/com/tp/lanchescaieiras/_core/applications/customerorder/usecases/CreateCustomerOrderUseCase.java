package br.com.tp.lanchescaieiras._core.applications.customerorder.usecases;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.CustomerOrderUseCaseUtils;
import br.com.tp.lanchescaieiras._core.commons.utils.integrations.CustomerOrderIntegrationUtil;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

public class CreateCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public CreateCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }

    public CustomerOrder execute(CustomerOrderDTO customerOrderDTO) {
        customerOrderDTO.setStatus("Checkout");
        CustomerOrder customerOrder = new CustomerOrder(customerOrderDTO);

        //Validação somente quando é informado id do cliente. Requer um id válido
        if (customerOrderDTO.getCustomer() != null && customerOrderDTO.getCustomer().getId() != null) {
            Integer customerId = customerOrderDTO.getCustomer().getId();
            customerOrder.setCustomer(CustomerOrderIntegrationUtil.getCustomerDetail(customerId,customerOrderGateway));
            if (customerOrder.getCustomer() ==null){
                throw new CustomerOrderException("Cliente com id "+ customerId +" não encontrado. Envie um pedido com cliente válido.",400);
            }
        }

        //Obtendo informações dos items de alimentação
        customerOrder = CustomerOrderUseCaseUtils.getFoodItemsDetails(customerOrder,customerOrderGateway);
        customerOrder = this.customerOrderGateway.createCustomerOrder(customerOrder);

        //TODO - implementar integrações com pagamento e notificações
        //this.customerOrderGateway.createPaymentCharge(customerOrder);
        //this.customerOrderGateway.sendNotification("CustomerOrder",1,"mensagem");

        return customerOrder;
    }


}
