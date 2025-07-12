package br.com.tp.lanchescaieiras._external.dataproxy;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder.JpaCustomerOrderFoodItemPostgresRepositoryImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder.JpaCustomerOrderPostgresRepositoryImpl;
import br.com.tp.lanchescaieiras._external.integrations.customer.CustomerIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.fooditem.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.payment.PaymentIntegrationImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderDataProxy implements CustomerOrderDatabase {

    private final JpaCustomerOrderPostgresRepositoryImpl jpaCustomerOrderPostgresRepository;
    private final JpaCustomerOrderFoodItemPostgresRepositoryImpl jpaCustomerOrderFoodItemPostgresRepository;
    private final CustomerIntegrationImpl customerIntegration;
    private final FoodItemIntegrationImpl foodItemIntegration;
    private final PaymentIntegrationImpl paymentIntegration;

    public CustomerOrderDataProxy(JpaCustomerOrderPostgresRepositoryImpl jpaCustomerOrderPostgresRepository,
                                  JpaCustomerOrderFoodItemPostgresRepositoryImpl jpaCustomerOrderFoodItemPostgresRepository,
                                  CustomerIntegrationImpl customerIntegration,
                                  FoodItemIntegrationImpl foodItemIntegration,
                                  PaymentIntegrationImpl paymentIntegration) {
        this.jpaCustomerOrderPostgresRepository = jpaCustomerOrderPostgresRepository;
        this.jpaCustomerOrderFoodItemPostgresRepository = jpaCustomerOrderFoodItemPostgresRepository;
        this.customerIntegration = customerIntegration;
        this.foodItemIntegration = foodItemIntegration;
        this.paymentIntegration = paymentIntegration;
    }

    @Override
    public CustomerOrderDTO save(CustomerOrderDTO customerOrderDTO) {
        CustomerOrderDTO newCustomerDTO = this.jpaCustomerOrderPostgresRepository.save(customerOrderDTO);
        newCustomerDTO.setFoodItems(customerOrderDTO.getFoodItems());
        for (CustomerOrderFoodItemDTO item : newCustomerDTO.getFoodItems()){
            item.setOrderId(newCustomerDTO.getId());
        }
        newCustomerDTO.setFoodItems(this.jpaCustomerOrderFoodItemPostgresRepository.saveAll(newCustomerDTO.getFoodItems()));
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
        CustomerOrderDTO customerOrderDTO = this.jpaCustomerOrderPostgresRepository.findById(customerOrderId);
        if (customerOrderDTO != null && includFoodItems == true)
            customerOrderDTO.setFoodItems(this.jpaCustomerOrderFoodItemPostgresRepository.findByCustomerOrderId(customerOrderDTO.getId()));
        return customerOrderDTO;
    }

    @Override
    public List<CustomerOrderDTO> getCustomerOrderByStatusList(List<Integer> statusListIds, Boolean includeFoodItems) {
        List<CustomerOrderDTO> customerOrderDTOList = this.jpaCustomerOrderPostgresRepository.findByStatusList(statusListIds);
        if (customerOrderDTOList != null && includeFoodItems == true){
            List<CustomerOrderFoodItemDTO> customerOrderFoodItemDTOList = getFoodItemsInCustomerOrdersIdList(customerOrderDTOList,includeFoodItems);
            customerOrderDTOList = setFoodItemsInCustomerOrder(customerOrderDTOList,customerOrderFoodItemDTOList);
        }
        return customerOrderDTOList;
    }

    @Override
    public CustomerOrderDTO updateCustomerOrder(CustomerOrderDTO updateCustomerOrderDTO) {
        return this.jpaCustomerOrderPostgresRepository.save(updateCustomerOrderDTO);
    }

    private List<CustomerOrderFoodItemDTO> getFoodItemsInCustomerOrdersIdList(List<CustomerOrderDTO> customerOrderDTOList, Boolean includeFoodItems){
            List<Integer> customerOrdersIdsList = customerOrderDTOList.stream()
                    .map(CustomerOrderDTO::getId)
                    .collect(Collectors.toList());
            return this.jpaCustomerOrderFoodItemPostgresRepository.findByCustomerOrderIdList(customerOrdersIdsList);
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
