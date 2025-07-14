package br.com.tp.lanchescaieiras._external.integrations.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._external.commons.utils.IntegrationUtil;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KitchenOrderIntegrationImpl implements KitchenOrderIntegration {

    private static final Logger log = LoggerFactory.getLogger(KitchenOrderIntegrationImpl.class);
    private final IntegrationConfig integrationConfig;

    public KitchenOrderIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void createKitchenOrder(CustomerOrderDTO customerOrderDTO) {
        RestTemplate restTemplate = new RestTemplate();
        String kitchenOrderUrl = integrationConfig.getKitchenOrdersUrl();
        KitchenOrderDTO kitchenOrderDTO = toKitchenOrderDTO(customerOrderDTO);
        log.info("Criando pedido na cozinha:\nUrl: {}\n RequestBody:\n {}", kitchenOrderUrl, IntegrationUtil.toJson(kitchenOrderDTO));
        IntegrationUtil.postForObject(kitchenOrderUrl, kitchenOrderDTO);
    }

    private KitchenOrderDTO toKitchenOrderDTO(CustomerOrderDTO customerOrderDTO) {
        KitchenOrderDTO kitchenOrderDTO = new KitchenOrderDTO();
        kitchenOrderDTO.setCustomerOrderId(customerOrderDTO.getId());
        kitchenOrderDTO.setFoodItems(customerOrderDTO.getFoodItems().stream()
                .map(this::toKitchenOrderFoodItemDTO)
                .toList());
        return kitchenOrderDTO;
    }

    private KitchenOrderFoodItemDTO toKitchenOrderFoodItemDTO(CustomerOrderFoodItemDTO customerOrderFoodItemDTO) {
        KitchenOrderFoodItemDTO kitchenOrderFoodItemDTO = new KitchenOrderFoodItemDTO();
        kitchenOrderFoodItemDTO.setName(customerOrderFoodItemDTO.getName());
        kitchenOrderFoodItemDTO.setDescription(customerOrderFoodItemDTO.getDescription());
        kitchenOrderFoodItemDTO.setNotes(customerOrderFoodItemDTO.getNotes());
        return kitchenOrderFoodItemDTO;
    }

}
