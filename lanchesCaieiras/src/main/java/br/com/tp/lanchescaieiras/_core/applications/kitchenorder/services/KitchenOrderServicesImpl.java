package br.com.tp.lanchescaieiras._core.applications.kitchenorder.services;

import br.com.tp.lanchescaieiras._core.applications.kitchenorder.usecases.KitchenOrderUseCases;
import br.com.tp.lanchescaieiras._core.domain.exceptions.KitchenOrderException;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderFoodItem;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderStatus;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;
import br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder.JpaKitchenOrderFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder.JpaKitchenOrderRepositoryImpl;
import br.com.tp.lanchescaieiras._external.integrations.customerorder.CustomerOrderIntegrationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class KitchenOrderServicesImpl implements KitchenOrderUseCases {

    private static final Logger log = LoggerFactory.getLogger(KitchenOrderServicesImpl.class);
    private final JpaKitchenOrderRepositoryImpl jpaKitchenOrderRepositoryImpl;
    private final JpaKitchenOrderFoodItemRepositoryImpl jpaKitchenOrderFoodItemRepository;
    private final CustomerOrderIntegrationImpl customerOrderIntegration;
    private final ApplicationEventPublisher eventPublisher;

    public KitchenOrderServicesImpl(JpaKitchenOrderRepositoryImpl jpaKitchenOrderRepositoryImpl,
                                    JpaKitchenOrderFoodItemRepositoryImpl jpaKitchenOrderFoodItemRepository,
                                    CustomerOrderIntegrationImpl customerOrderIntegration,
                                    ApplicationEventPublisher eventPublisher) {
        this.jpaKitchenOrderRepositoryImpl = jpaKitchenOrderRepositoryImpl;
        this.jpaKitchenOrderFoodItemRepository = jpaKitchenOrderFoodItemRepository;
        this.customerOrderIntegration = customerOrderIntegration;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public KitchenOrder createKitchenOrder(KitchenOrder kitchenOrder) {
        KitchenOrder createdKitchenOrder = jpaKitchenOrderRepositoryImpl.findByCustomerOrderId(kitchenOrder.getCustomerOrderId());

        log.info("Validando se já há preparo para o pedido: {}", kitchenOrder.getCustomerOrderId());

        if (createdKitchenOrder != null) {
            log.info("Já existe um pedido de preparo para o pedido do cliente id: {}", kitchenOrder.getCustomerOrderId());

            throw new KitchenOrderException("Já existe um pedido de preparo para o pedido do cliente id: " + kitchenOrder.getCustomerOrderId(), 409);
        }

        log.info("Criando novo pedido de preparo {}", kitchenOrder);
        if (kitchenOrder.getStatus() == null || !kitchenOrder.getStatus().equalsIgnoreCase("RECEIVED")) {
            log.info("Definindo Status do pedido para Received");
            kitchenOrder.setStatus(KitchenOrderStatus.RECEIVED.getDescription());
        }
        createdKitchenOrder = jpaKitchenOrderRepositoryImpl.save(kitchenOrder);
        log.info("Ordem criada com sucesso!");
        createdKitchenOrder.setFoodItems(new ArrayList<>());

        log.info("Criando itens de alimentação da ordem");
        for (KitchenOrderFoodItem kitchenOrderFoodItem : kitchenOrder.getFoodItems()) {
            kitchenOrderFoodItem = jpaKitchenOrderFoodItemRepository.save(kitchenOrderFoodItem, createdKitchenOrder.getId());
            createdKitchenOrder.getFoodItems().add(kitchenOrderFoodItem);
        }

        publishNotification("KITCHEN_ORDER_RECEIVED", createdKitchenOrder.getId(), "Novo Pedido de preparo recebido.");
        return createdKitchenOrder;
    }

    @Override
    public KitchenOrder findById(Integer id, Boolean includeFoodItems) {

        log.info("Buscando o preparo com o ID {}. includeFoodItems = {}", id, includeFoodItems);
        KitchenOrder kitchenOrder = jpaKitchenOrderRepositoryImpl.findById(id);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Preparo id:" + id + " não encontrado", 404);
        }
        if (includeFoodItems) {
            kitchenOrder.setFoodItems(jpaKitchenOrderFoodItemRepository.findByKitchenOrderId(kitchenOrder.getId()));
        }

        return kitchenOrder;
    }

    @Override
    public KitchenOrder getKitchenOrderByCustomerOrderById(Integer customerOrderId, Boolean includeFoodItems) {
        KitchenOrder kitchenOrder = jpaKitchenOrderRepositoryImpl.findByCustomerOrderId(customerOrderId);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado preparo com pedido: " + customerOrderId, 404);
        }
        if (includeFoodItems) {
            kitchenOrder.setFoodItems(jpaKitchenOrderFoodItemRepository.findByKitchenOrderId(kitchenOrder.getId()));
        }
        return kitchenOrder;
    }

    @Override
    public List<KitchenOrder> findByStatus(String status, Boolean includeFoodItems) {
        log.info("Buscando o pedido com o status {}. includeFoodItems = {}", status, includeFoodItems);
        Integer statusId = KitchenOrderStatus.fromDescription(status).getId();
        List<KitchenOrder> kitchenOrders = jpaKitchenOrderRepositoryImpl.findByStatusId(statusId);
        if (includeFoodItems) {
            for (KitchenOrder kitchenOrder : kitchenOrders) {
                kitchenOrder.setFoodItems(jpaKitchenOrderFoodItemRepository.findByKitchenOrderId(kitchenOrder.getId()));
            }
        }
        return kitchenOrders;
    }

    @Override
    public KitchenOrder updateStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder) {
        log.info("Atualizando status do preparo {} para {}. forceUpdate = {}", kitchenOrderId, newStatus, forceUpdate);
        KitchenOrder kitchenOrder = jpaKitchenOrderRepositoryImpl.findById(kitchenOrderId);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado order de preparo com id: " + kitchenOrderId, 404);
        }
        if (forceUpdate == false) {
            validateNewStatus(kitchenOrder.getStatus(), newStatus);
        }

        kitchenOrder.setStatus(KitchenOrderStatus.fromDescription(newStatus).getDescription());
        kitchenOrder = jpaKitchenOrderRepositoryImpl.save(kitchenOrder);

        if (updateCustomerOrder && !kitchenOrder.getStatus().equalsIgnoreCase("FINISHED")) {
            log.info("Atualizando Pedido do cliente id: {} para {}", kitchenOrder.getCustomerOrderId(), newStatus);
            customerOrderIntegration.updateCustomerOrderStatus(kitchenOrder.getCustomerOrderId(), newStatus);
        }
        log.info("Status atualizado para: {}", newStatus);
        return kitchenOrder;
    }

    private void validateNewStatus(String actualStatus, String newStatus) {
        log.info("Validando se é permitido atualizar preparo de {} para {}", actualStatus, newStatus);
        Integer actualStatusId = KitchenOrderStatus.fromDescription(actualStatus).getId();
        Integer newStatusId = KitchenOrderStatus.fromDescription(newStatus).getId();
        if (actualStatusId + 1 != newStatusId) {
            throw new KitchenOrderException("Erro na atualização no status da order de preparo. Não é permitido atualizar o status de: " + actualStatus + " para: " + newStatus, 400);
        }
        log.info("Validado que atualização do preparo de {} para {}", actualStatus, newStatus);
    }

    private void publishNotification(String artetefactType, Integer artifactId, String message) {
        eventPublisher.publishEvent(new Notification(this, null, artetefactType, artifactId, message));
    }

}






