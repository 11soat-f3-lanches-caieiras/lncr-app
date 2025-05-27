package br.com.tp.lanchescaieiras.customerorder.application.services;

import br.com.tp.lanchescaieiras.commons.domain.Notification;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.CustomerIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.KitchenOrderIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations.PaymentIntegrationImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories.JpaCustomerOrderFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.repositories.JpaCustomerOrderRepositoryImpl;
import br.com.tp.lanchescaieiras.customerorder.application.usecases.CustomerOrderUseCases;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderStatus;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CustomerOrderServicesImpl implements CustomerOrderUseCases {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderServicesImpl.class);
    private final JpaCustomerOrderRepositoryImpl jpaCustomerOrderRepositoryImpl;
    private final JpaCustomerOrderFoodItemRepositoryImpl jpaCustomerOrderFoodItemRepository;
    private final FoodItemIntegrationImpl foodItemIntegration;
    private final CustomerIntegrationImpl customerIntegration;
    private final KitchenOrderIntegrationImpl kitchenOrderIntegration;
    private final PaymentIntegrationImpl paymentIntegration;
    private final ApplicationEventPublisher eventPublisher;

    public CustomerOrderServicesImpl(JpaCustomerOrderRepositoryImpl jpaCustomerOrderRepositoryImpl,
                                     JpaCustomerOrderFoodItemRepositoryImpl jpaCustomerOrderFoodItemRepository,
                                     FoodItemIntegrationImpl foodItemIntegration,
                                     CustomerIntegrationImpl customerIntegration,
                                     KitchenOrderIntegrationImpl kitchenOrderIntegration,
                                     PaymentIntegrationImpl paymentIntegration,
                                     ApplicationEventPublisher eventPublisher) {
        this.jpaCustomerOrderRepositoryImpl = jpaCustomerOrderRepositoryImpl;
        this.jpaCustomerOrderFoodItemRepository = jpaCustomerOrderFoodItemRepository;
        this.foodItemIntegration = foodItemIntegration;
        this.customerIntegration = customerIntegration;
        this.kitchenOrderIntegration = kitchenOrderIntegration;
        this.paymentIntegration = paymentIntegration;
        this.eventPublisher = eventPublisher;

    }

    @Override
    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {

        log.info("Criando novo pedido para o cliente {}", customerOrder);

        if (customerOrder.getStatus() == null || customerOrder.getStatus().toUpperCase() != "CHECKOUT") {
            log.info("Definindo Status do pedido para Checkout");
            customerOrder.setStatus(CustomerOrderStatus.CHECKOUT.getDescription()); //Valida que o pedido seja criado no status checkout
        }

        customerOrder = validateCustomer(customerOrder);
        customerOrder = customerOrderFoodsItemDetails(customerOrder);
        customerOrder.setTotalCost(); //Calcular valor total do pedido

        CustomerOrder createdCustomerOrder = jpaCustomerOrderRepositoryImpl.save(customerOrder);
        log.info("Ordem criada com sucesso!");
        createdCustomerOrder.setFoodItems(new ArrayList<>());

        log.info("Criando itens de alimentação da ordem");
        for (CustomerOrderFoodItem customerOrderFoodItem : customerOrder.getFoodItems()) {
            customerOrderFoodItem = jpaCustomerOrderFoodItemRepository.save(customerOrderFoodItem, createdCustomerOrder.getId());
            createdCustomerOrder.getFoodItems().add(customerOrderFoodItem);
        }

        paymentIntegration.createPayment(chargeRequest(createdCustomerOrder));

        return createdCustomerOrder;
    }

    @Override
    public CustomerOrder findById(Integer id, Boolean includeFoodItems) {
        log.info("Buscando o pedido com o ID {}. includeFoodItems = {}", id, includeFoodItems);
        CustomerOrder customerOrder = jpaCustomerOrderRepositoryImpl.findById(id);
        customerOrder = validateCustomer(customerOrder);
        customerOrder = includeFoodItemsDetails(customerOrder, includeFoodItems);
        return customerOrder;
    }

    @Override
    public List<CustomerOrder> findByStatus(String status, Boolean includeFoodItems) {
        log.info("Buscando o pedido com o status {}. includeFoodItems = {}", status, includeFoodItems);
        Integer statusId = CustomerOrderStatus.fromDescription(status).getId();
        List<CustomerOrder> customerOrders = jpaCustomerOrderRepositoryImpl.findByStatusId(statusId);
        for (CustomerOrder customerOrder : customerOrders) {
            customerOrder = validateCustomer(customerOrder);
            customerOrder = includeFoodItemsDetails(customerOrder, includeFoodItems);
        }
        return customerOrders;
    }

    @Override
    public CustomerOrder updateStatusById(Integer customerOrderId, String newStatus, Boolean forceUpdate) {
        log.info("Atualizando status do pedido {} para {}. forceUpdate = {}", customerOrderId, newStatus, forceUpdate);
        CustomerOrder customerOrder = jpaCustomerOrderRepositoryImpl.findById(customerOrderId);

        if (customerOrder == null) {
            throw new CustomerOrderException("Não encontrado pedido com id: " + customerOrderId, 404);
        }
        if (forceUpdate == false) {
            validateNewStatus(customerOrder.getStatus(), newStatus);
        }
        customerOrder.setStatus(CustomerOrderStatus.fromDescription(newStatus).getDescription());
        customerOrder = jpaCustomerOrderRepositoryImpl.save(customerOrder);

        if (customerOrder.getStatus().equals(CustomerOrderStatus.RECEIVED.getDescription())) {
            log.info("Pedido recebido. Solicitando nova ordem de prepraro");
            customerOrder = includeFoodItemsDetails(customerOrder, true);
            String kitchenOrder = customerOrderToNewKitchenOrder(customerOrder);
            kitchenOrderIntegration.sendKitchenOrder(kitchenOrder);
        }

        if (customerOrder.getStatus().equals(CustomerOrderStatus.READY.getDescription())) {
            log.info("Notificando Cliente que o pedido está pronto");
            publishNotification("CUSTOMER_ORDER_READY", customerOrder.getId(), "Pedido " + customerOrder.getId() + " está pronto para retirada.");
        }

        return customerOrder;
    }

    public void validateNewStatus(String actualStatus, String newStatus) {
        log.info("Validando se é permitido atualizar pedido de {} para {}", actualStatus, newStatus);
        Integer actualStatusId = CustomerOrderStatus.fromDescription(actualStatus).getId();
        Integer newStatusId = CustomerOrderStatus.fromDescription(newStatus).getId();
        if (actualStatusId + 1 != newStatusId) {
            throw new CustomerOrderException("Erro na atualização no status do pedido. Não é permitido atualizar o status de: " + actualStatus + " para: " + newStatus, 400);
        }
        log.info("Validado que atualização do pedido de {} para {}", actualStatus, newStatus);
    }

    private CustomerOrder validateCustomer(CustomerOrder customerOrder) {
        log.info("Validando o cliente {}", customerOrder.getCustomer());
        if (customerOrder.getCustomer() != null && customerOrder.getCustomer().getId() != null) {
            log.info("Buscando informações do cliente via integração");
            customerOrder.setCustomer(customerIntegration.getCustomerOrderCustomerDetails(customerOrder.getCustomer().getId()));
        }
        return customerOrder;
    }

    private CustomerOrder includeFoodItemsDetails(CustomerOrder customerOrder, Boolean includeFoodItems) {
        log.info("Incluindo detalhes de items de alimentações");
        if (includeFoodItems) {
            customerOrder.setFoodItems(jpaCustomerOrderFoodItemRepository.findByCustomerOrderId(customerOrder.getId()));
            customerOrder = customerOrderFoodsItemDetails(customerOrder);
        }
        return customerOrder;
    }


    private CustomerOrder customerOrderFoodsItemDetails(CustomerOrder customerOrder) {
        if (customerOrder.getFoodItems() == null || customerOrder.getFoodItems().isEmpty()) {
            log.info("Nenhum item de alimentação encontrado no pedido {}", customerOrder.getId());
            throw new CustomerOrderException("Nenhum item de alimentação encontrado no pedido: " + customerOrder.getId(), 404);
        }

        List<Integer> foodItemIds = customerOrder.getFoodItems().stream().map(CustomerOrderFoodItem::getId)
                .collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());

        List<CustomerOrderFoodItem> customerOrderFoodItemsDetails = getFoodItemsDetailsIntegration(foodItemIds);
        if (customerOrderFoodItemsDetails.isEmpty()) {
            log.info("Nenhum detalhe de item de alimentação encontrado para o pedido {}", customerOrder.getId());
            throw new CustomerOrderException("Nenhum detalhe de item de alimentação encontrado para o pedido: " + customerOrder.getId(), 404);
        }

        customerOrder.setFoodItems(mergeFoodItemDetails(customerOrder.getFoodItems(), customerOrderFoodItemsDetails));
        customerOrder.setFoodItems(
                customerOrder.getFoodItems().stream()
                        .filter(foodItem -> foodItem.getPrice() != null)
                        .collect(Collectors.toList()));
        return customerOrder;

    }

    private List<CustomerOrderFoodItem> getFoodItemsDetailsIntegration(List<Integer> foodItemIds) {
        List<CustomerOrderFoodItem> customerOrderFoodItemsDetails = new ArrayList<>();

        for (Integer foodItemId : foodItemIds) {
            log.info("Buscando detalhes de dos itens de alimentação {}", foodItemId);
            CustomerOrderFoodItem customerFoodItemDetail = foodItemIntegration.getFoodItemsDetails(foodItemId);
            if (customerFoodItemDetail != null) {
                customerOrderFoodItemsDetails.add(customerFoodItemDetail);
            }
        }
        return customerOrderFoodItemsDetails;
    }

    private List<CustomerOrderFoodItem> mergeFoodItemDetails(List<CustomerOrderFoodItem> foodItems,
                                                             List<CustomerOrderFoodItem> customerOrderFoodItemsDetails) {
        log.info("Atualizando itens de alimentação com seus detalhes");
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

    private String chargeRequest(CustomerOrder customerOrder) {
        try {
            Map<String, Object> chargeRequest = new HashMap<>();
            chargeRequest.put("orderId", customerOrder.getId());
            chargeRequest.put("amount", customerOrder.getTotalCost());

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.writeValueAsString(chargeRequest);
        } catch (Exception e) {
            throw new CustomerOrderException("Erro ao solicitar cobrança para pedido: " + customerOrder, 500);
        }

    }

    private String customerOrderToNewKitchenOrder(CustomerOrder customerOrder) {
        try {
            Map<String, Object> kitchenOrder = new HashMap<>();
            kitchenOrder.put("customerOrderId", customerOrder.getId());
            List<Map<String, Object>> foodItems = customerOrder.getFoodItems().stream().map(foodItem -> {
                Map<String, Object> foodItemMap = new HashMap<>();
                foodItemMap.put("name", foodItem.getName());
                foodItemMap.put("description", foodItem.getDescription());
                foodItemMap.put("notes", foodItem.getNotes());
                return foodItemMap;
            }).collect(Collectors.toList());
            kitchenOrder.put("foodItems", foodItems);

            ObjectMapper objectMapper = new ObjectMapper();
            log.info("Criado requisição para enviar para cozinha\n{}", kitchenOrder);

            return objectMapper.writeValueAsString(kitchenOrder);
        } catch (Exception e) {
            throw new CustomerOrderException("Erro ao criar order de preparo para o pedido " + customerOrder, 500);
        }
    }

    private void publishNotification(String artetefactType, Integer artifactId, String message) {
        eventPublisher.publishEvent(new Notification(this, null, artetefactType, artifactId, message));
    }
}



