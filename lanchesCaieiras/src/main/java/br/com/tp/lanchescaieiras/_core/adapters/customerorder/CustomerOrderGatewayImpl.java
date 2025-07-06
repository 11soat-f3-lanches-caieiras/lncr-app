package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;

import java.util.List;

public class CustomerOrderGatewayImpl implements CustomerOrderGateway {

    private final CustomerOrderDatabase customerOrderDatabase;
    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderGatewayImpl(CustomerOrderDatabase customerOrderDatabase, CustomerOrderMapper customerOrderMapper) {
        this.customerOrderDatabase = customerOrderDatabase;
        this.customerOrderMapper = customerOrderMapper;
    }

    @Override
    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
        CustomerOrderDTO customerOrderDTO = this.customerOrderMapper.customerOrderToDTO(customerOrder);
        customerOrderDTO = this.customerOrderDatabase.save(customerOrderDTO);
        return this.customerOrderMapper.customerOrderToDomain(customerOrderDTO);
    }

    @Override
    public CustomerOrderCustomer getCustomerDetails(Integer customerId) {
        return this.customerOrderMapper.customerIrOrderToDomain(this.customerOrderDatabase.getCustomerDetails(customerId));
    }

    @Override
    public List<CustomerOrderFoodItem> getFoodItemsDetails(List<Integer> foodItemListIds) {
        return this.customerOrderDatabase.getFoodItemsDetails(foodItemListIds)
                .stream()
                .map(customerOrderMapper::foodItemInOrderToDomain)
                .toList();
    }

    @Override
    public void createPaymentCharge(CustomerOrder customerOrder) {
        this.customerOrderDatabase.createPaymentCharge(customerOrder.getId(),customerOrder.getTotalCost());
    }

    @Override
    public void sendNotification(String notificationSource, Integer artefactId, String message) {
        this.customerOrderDatabase.sendNotification(notificationSource,artefactId,message);
    }
}

