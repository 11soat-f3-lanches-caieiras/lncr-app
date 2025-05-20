package br.com.tp.lanchescaieiras.customerorder.application.services;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.CustomerIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories.JpaCustomerOrderFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories.JpaCustomerOrderRepositoryImpl;
import br.com.tp.lanchescaieiras.customerorder.application.usecases.CustomerOrderUseCases;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class CustomerOrderServicesImpl implements CustomerOrderUseCases {

    private final JpaCustomerOrderRepositoryImpl jpaCustomerOrderRepositoryImpl;
    private final JpaCustomerOrderFoodItemRepositoryImpl jpaCustomerOrderFoodItemRepository;
    private final FoodItemIntegrationImpl foodItemIntegration;
    private final CustomerIntegrationImpl customerIntegration;

    public CustomerOrderServicesImpl(JpaCustomerOrderRepositoryImpl jpaCustomerOrderRepositoryImpl,
                                     JpaCustomerOrderFoodItemRepositoryImpl jpaCustomerOrderFoodItemRepository,
                                     FoodItemIntegrationImpl foodItemIntegration,
                                     CustomerIntegrationImpl customerIntegration) {
        this.jpaCustomerOrderRepositoryImpl = jpaCustomerOrderRepositoryImpl;
        this.jpaCustomerOrderFoodItemRepository = jpaCustomerOrderFoodItemRepository;
        this.foodItemIntegration = foodItemIntegration;
        this.customerIntegration = customerIntegration;
    }

    @Override
    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
        if (customerOrder.getStatus() == null || customerOrder.getStatus().toUpperCase() != "CHECKOUT") {
            customerOrder.setStatus(CustomerOrderStatus.CHECKOUT.getDescription()); //Valida que o pedido seja criado no status checkout
        }
        customerOrder = validateCustomer(customerOrder);
        customerOrder = customerOrderFoodsItemDetails(customerOrder);
        customerOrder.setTotalCost(); //Calcular valor total do pedido

        CustomerOrder createdCustomerOrder = jpaCustomerOrderRepositoryImpl.save(customerOrder);
        createdCustomerOrder.setFoodItems(new ArrayList<>());

        for (CustomerOrderFoodItem customerOrderFoodItem : customerOrder.getFoodItems()) {
            customerOrderFoodItem = jpaCustomerOrderFoodItemRepository.save(customerOrderFoodItem, createdCustomerOrder.getId());
            createdCustomerOrder.getFoodItems().add(customerOrderFoodItem);
        }
        return createdCustomerOrder;
    }

    @Override
    public CustomerOrder findById(Integer id, Boolean includeFoodItems) {
        CustomerOrder customerOrder = jpaCustomerOrderRepositoryImpl.findById(id);
        customerOrder = validateCustomer(customerOrder);
        customerOrder = includeFoodItemsDetails(customerOrder, includeFoodItems);
        return customerOrder;
    }

    @Override
    public List<CustomerOrder> findByStatus(String status, Boolean includeFoodItems) {
        Integer statusId = CustomerOrderStatus.fromDescription(status).getId();
        List<CustomerOrder> customerOrders = jpaCustomerOrderRepositoryImpl.findByStatusId(statusId);
        for(CustomerOrder customerOrder : customerOrders) {
            customerOrder = validateCustomer(customerOrder);
            customerOrder = includeFoodItemsDetails(customerOrder, includeFoodItems);
        }
        return customerOrders;
    }


    private CustomerOrder validateCustomer(CustomerOrder customerOrder) {
        if (customerOrder.getCustomer() != null && customerOrder.getCustomer().getId() != null) {
            customerOrder.setCustomer(customerIntegration.getCustomerOrderCustomerDetails(customerOrder.getCustomer().getId()));
        }
        return customerOrder;
    }

    private CustomerOrder includeFoodItemsDetails(CustomerOrder customerOrder, Boolean includeFoodItems) {
        if (includeFoodItems) {
            customerOrder.setFoodItems(jpaCustomerOrderFoodItemRepository.findByCustomerOrderId(customerOrder.getId()));
            customerOrder = customerOrderFoodsItemDetails(customerOrder);
        }
        return customerOrder;
    }


    private CustomerOrder customerOrderFoodsItemDetails(CustomerOrder customerOrder){
        List<Integer> foodItemIds = customerOrder.getFoodItems().stream().map(CustomerOrderFoodItem::getId)
                .collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
        List<CustomerOrderFoodItem> customerOrderFoodItemsDetails = getFoodItemsDetailsIntegration(foodItemIds);
        customerOrder.setFoodItems(mergeFoodItemDetails(customerOrder.getFoodItems(), customerOrderFoodItemsDetails));
        return customerOrder;
    }

    private List<CustomerOrderFoodItem> getFoodItemsDetailsIntegration(List<Integer> foodItemIds) {
        List<CustomerOrderFoodItem> customerOrderFoodItemsDetails = new ArrayList<>();
        for (Integer foodItemId : foodItemIds) {
            customerOrderFoodItemsDetails.add(foodItemIntegration.getFoodItemsDetails(foodItemId));
        }
        return customerOrderFoodItemsDetails;
    }

    private List<CustomerOrderFoodItem> mergeFoodItemDetails(List<CustomerOrderFoodItem> foodItems,
                                                             List<CustomerOrderFoodItem> customerOrderFoodItemsDetails) {
        for (CustomerOrderFoodItem customerOrderFoodItem : foodItems) {
            for (CustomerOrderFoodItem customerOrderFoodItemDetails : customerOrderFoodItemsDetails) {
                if (customerOrderFoodItem.getId() == customerOrderFoodItemDetails.getId()) {
                    customerOrderFoodItem.setName(customerOrderFoodItemDetails.getName());
                    customerOrderFoodItem.setDescription(customerOrderFoodItemDetails.getDescription());
                    customerOrderFoodItem.setPrice(customerOrderFoodItemDetails.getPrice());
                    }
                }
            }
        return foodItems;
        }
}



