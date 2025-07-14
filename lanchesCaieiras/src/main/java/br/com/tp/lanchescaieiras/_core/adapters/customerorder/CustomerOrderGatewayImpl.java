package br.com.tp.lanchescaieiras._core.adapters.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
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
    public List<CustomerOrderCustomer> getCustomerDetailsList(List<Integer> customerIdList) {
        return this.customerOrderDatabase.getCustomerDetailsList(customerIdList).stream().map(customerOrderMapper::customerInOrderToDomain).toList();
    }

    @Override
    public CustomerOrderCustomer getCustomerDetails(Integer customerId) {
        CustomerOrderCustomerDTO customerOrderCustomerDTO = this.customerOrderDatabase.getCustomerDetails(customerId);
        return this.customerOrderMapper.customerInOrderToDomain(customerOrderCustomerDTO);
    }

    @Override
    public List<CustomerOrderFoodItem> getFoodItemsDetails(List<Integer> foodItemListIds) {
        return this.customerOrderDatabase.getFoodItemsDetailsList(foodItemListIds)
                .stream()
                .map(customerOrderMapper::foodItemInOrderToDomain)
                .toList();
    }

    @Override
    public void createPaymentCharge(CustomerOrder customerOrder) {
        this.customerOrderDatabase.createPaymentCharge(customerOrder.getId(),customerOrder.getTotalCost());
    }

    @Override
    public void sendNotification(String notificationType, Integer artefactId, String message) {
        this.customerOrderDatabase.sendNotification(notificationType,artefactId,message);
    }

    @Override
    public CustomerOrder getCustomerOrderById(Integer customerOrderId) {
        return getCustomerOrderById(customerOrderId,false);
    }

    @Override
    public CustomerOrder getCustomerOrderById(Integer customerOrderId, Boolean includFoodItems) {
        CustomerOrderDTO customerOrderDTO = this.customerOrderDatabase.getCustomerOrderById(customerOrderId,includFoodItems);
        return this.customerOrderMapper.customerOrderToDomain(customerOrderDTO);
    }

    @Override
    public List<CustomerOrder> getCustomerOrderByStatusList(List<Integer> statusListIds, Boolean includeFoodItems) {
        List<CustomerOrderDTO> customerOrderDTOList = this.customerOrderDatabase.getCustomerOrderByStatusList(statusListIds,includeFoodItems);
        return customerOrderDTOList.stream().map(customerOrderMapper::customerOrderToDomain).toList();
    }

    @Override
    public CustomerOrder updateCustomerOrder(CustomerOrder updatedCustomerOrder) {

        CustomerOrderDTO updatedCustomerOrderDTO = this.customerOrderMapper.customerOrderToDTO(updatedCustomerOrder);
        updatedCustomerOrderDTO = this.customerOrderDatabase.updateCustomerOrder(updatedCustomerOrderDTO);
        return this.customerOrderMapper.customerOrderToDomain(updatedCustomerOrderDTO);
    }

    @Override
    public void createKitchenOrder(CustomerOrder updateCustomerOrder) {
        this.customerOrderDatabase.createKitchenOrder(this.customerOrderMapper.customerOrderToDTO(updateCustomerOrder));
    }
}

