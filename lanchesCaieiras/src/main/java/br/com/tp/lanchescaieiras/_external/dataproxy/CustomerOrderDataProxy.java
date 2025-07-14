package br.com.tp.lanchescaieiras._external.dataproxy;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder.*;
import br.com.tp.lanchescaieiras._external.integrations.customer.CustomerIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.fooditem.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.kitchenorder.KitchenOrderIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.payment.PaymentIntegrationImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerOrderDataProxy implements CustomerOrderDatabase {

    private final JpaCustomerOrderRepositoryImpl jpaCustomerOrderRepositoryImpl;
    private final JpaCustomerOrderRepository jpaCustomerOrderRepository;
    private final JpaCustomerOrderFoodItemRepositoryImpl jpaCustomerOrderFoodItemRepositoryImpl;
    private final JpaCustomerOrderFoodItemRepository jpaCustomerOrderFoodItemRepository;
    private final CustomerIntegrationImpl customerIntegration;
    private final FoodItemIntegrationImpl foodItemIntegration;
    private final PaymentIntegrationImpl paymentIntegration;
    private final KitchenOrderIntegrationImpl kitchenOrderIntegrationImpl;
    private final JpaCustomerOrderMapper jpaCustomerOrderMapper;

    public CustomerOrderDataProxy(JpaCustomerOrderRepositoryImpl jpaCustomerOrderRepositoryImpl, JpaCustomerOrderRepository jpaCustomerOrderRepository, JpaCustomerOrderFoodItemRepositoryImpl jpaCustomerOrderFoodItemRepositoryImpl, JpaCustomerOrderFoodItemRepository jpaCustomerOrderFoodItemRepository, CustomerIntegrationImpl customerIntegration, FoodItemIntegrationImpl foodItemIntegration, PaymentIntegrationImpl paymentIntegration, KitchenOrderIntegrationImpl kitchenOrderIntegrationImpl, JpaCustomerOrderMapper jpaCustomerOrderMapper) {
        this.jpaCustomerOrderRepositoryImpl = jpaCustomerOrderRepositoryImpl;
        this.jpaCustomerOrderRepository = jpaCustomerOrderRepository;
        this.jpaCustomerOrderFoodItemRepositoryImpl = jpaCustomerOrderFoodItemRepositoryImpl;
        this.jpaCustomerOrderFoodItemRepository = jpaCustomerOrderFoodItemRepository;
        this.customerIntegration = customerIntegration;
        this.foodItemIntegration = foodItemIntegration;
        this.paymentIntegration = paymentIntegration;
        this.kitchenOrderIntegrationImpl = kitchenOrderIntegrationImpl;
        this.jpaCustomerOrderMapper = jpaCustomerOrderMapper;
    }

    @Override
    public CustomerOrderDTO save(CustomerOrderDTO customerOrderDTO) {
        CustomerOrderDTO newCustomerDTO = this.jpaCustomerOrderRepositoryImpl.save(customerOrderDTO,jpaCustomerOrderRepository,jpaCustomerOrderMapper);
        newCustomerDTO.setFoodItems(customerOrderDTO.getFoodItems());
        for (CustomerOrderFoodItemDTO item : newCustomerDTO.getFoodItems()){
            item.setOrderId(newCustomerDTO.getId());
        }
        newCustomerDTO.setFoodItems(this.jpaCustomerOrderFoodItemRepositoryImpl.saveAll(newCustomerDTO.getFoodItems(),jpaCustomerOrderFoodItemRepository,jpaCustomerOrderMapper));
        return newCustomerDTO;
    }

    @Override
    public CustomerOrderCustomerDTO getCustomerDetails(Integer customerId) {
        return this.customerIntegration.getCustomerDetails(customerId);
    }

    @Override
    public List<CustomerOrderCustomerDTO> getCustomerDetailsList(List<Integer> customerIdList) {
        return this.customerIntegration.getCustomerDetailsList(customerIdList);
    }

    @Override
    public List<CustomerOrderFoodItemDTO> getFoodItemsDetailsList(List<Integer> foodItemListIds) {
        return this.foodItemIntegration.getFoodItemDetailList(foodItemListIds);
    }

    @Override
    public void createPaymentCharge(Integer customerOrderId, Double totalCost) {
        this.paymentIntegration.createPayment(customerOrderId,totalCost);
    }

    @Override
    public void sendNotification(String notificationSource, Integer artefactId, String message) {
        //implementar envio de notificação
    }

    @Override
    public CustomerOrderDTO getCustomerOrderById(Integer customerOrderId, Boolean includFoodItems) {
        CustomerOrderDTO customerOrderDTO = this.jpaCustomerOrderRepositoryImpl.findById(customerOrderId,jpaCustomerOrderRepository,jpaCustomerOrderMapper);
        if (customerOrderDTO != null && includFoodItems == true)
            customerOrderDTO.setFoodItems(this.jpaCustomerOrderFoodItemRepositoryImpl.findByCustomerOrderId(customerOrderDTO.getId(),jpaCustomerOrderFoodItemRepository,jpaCustomerOrderMapper));
        return customerOrderDTO;
    }

    @Override
    public List<CustomerOrderDTO> getCustomerOrderByStatusList(List<Integer> statusListIds, Boolean includeFoodItems) {
        List<CustomerOrderDTO> customerOrderDTOList = this.jpaCustomerOrderRepositoryImpl.findByStatusList(statusListIds,jpaCustomerOrderRepository,jpaCustomerOrderMapper);
        if (customerOrderDTOList != null && includeFoodItems == true){
            List<CustomerOrderFoodItemDTO> customerOrderFoodItemDTOList = getFoodItemsInCustomerOrdersIdList(customerOrderDTOList,includeFoodItems);
            customerOrderDTOList = setFoodItemsInCustomerOrder(customerOrderDTOList,customerOrderFoodItemDTOList);
        }
        return customerOrderDTOList;
    }

    @Override
    public CustomerOrderDTO updateCustomerOrder(CustomerOrderDTO updateCustomerOrderDTO) {
        return this.jpaCustomerOrderRepositoryImpl.save(updateCustomerOrderDTO, jpaCustomerOrderRepository, jpaCustomerOrderMapper);
    }

    @Override
    public void createKitchenOrder(CustomerOrderDTO customerOrderDTO) {
        this.kitchenOrderIntegrationImpl.createKitchenOrder(customerOrderDTO);
    }

    private List<CustomerOrderFoodItemDTO> getFoodItemsInCustomerOrdersIdList(List<CustomerOrderDTO> customerOrderDTOList, Boolean includeFoodItems){
            List<Integer> customerOrdersIdsList = customerOrderDTOList.stream()
                    .map(CustomerOrderDTO::getId)
                    .collect(Collectors.toList());
            return this.jpaCustomerOrderFoodItemRepositoryImpl.findByCustomerOrderIdList(customerOrdersIdsList,jpaCustomerOrderFoodItemRepository,jpaCustomerOrderMapper);
    }

    private List<CustomerOrderDTO> setFoodItemsInCustomerOrder(List<CustomerOrderDTO> customerOrderDTOList, List<CustomerOrderFoodItemDTO> customerOrderFoodItemDTOList){
        for (CustomerOrderDTO customerOrder : customerOrderDTOList){
            customerOrder.setFoodItems(customerOrderFoodItemDTOList.stream()
                    .filter(fooditem -> fooditem.getOrderId().equals(customerOrder.getId()))
                    .collect(Collectors.toList())
            );
        }
        return customerOrderDTOList;
    }
}
