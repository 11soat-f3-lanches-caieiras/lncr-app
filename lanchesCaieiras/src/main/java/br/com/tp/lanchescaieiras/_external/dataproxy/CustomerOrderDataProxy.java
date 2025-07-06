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

import java.util.ArrayList;
import java.util.List;

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
    public List<CustomerOrderFoodItemDTO> getFoodItemsDetails(List<Integer> foodItemListIds) {
        List<CustomerOrderFoodItemDTO> foodItemsDetailList = new ArrayList<>();
        for (Integer fooditemId : foodItemListIds){
            CustomerOrderFoodItemDTO customerOrderFoodItemDTO = this.foodItemIntegration.getFoodItemDetailsFromCustomerOrder(fooditemId);
            if(customerOrderFoodItemDTO != null) {
                foodItemsDetailList.add(customerOrderFoodItemDTO);
            }
        }
        return foodItemsDetailList;
    }

    @Override
    public void createPaymentCharge(Integer id, Double totalCost) {
        //this.paymentIntegration.createPayment();
    }

    @Override
    public void sendNotification(String notificationSource, Integer artefactId, String message) {
        //implementar envio de notificação
    }


}
