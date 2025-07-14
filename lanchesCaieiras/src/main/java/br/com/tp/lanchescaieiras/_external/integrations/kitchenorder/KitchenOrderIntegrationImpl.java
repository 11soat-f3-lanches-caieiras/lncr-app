package br.com.tp.lanchescaieiras._external.integrations.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.domain.exceptions.KitchenOrderException;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

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
        String kitchenOrderUrl = setPostUrl();
        KitchenOrderDTO kitchenOrderDTO = toKitchenOrderDTO(customerOrderDTO);
        log.info("Criando pedido na cozinha:\nUrl: {}\n RequestBody:\n {}", kitchenOrderUrl,kitchenOrderToJson(kitchenOrderDTO));
        CompletableFuture.runAsync(() -> {
            try {
                restTemplate.postForObject(kitchenOrderUrl, setRequestEntity(kitchenOrderDTO), String.class);
            } catch (Exception e) {
                throw new KitchenOrderException("Erro ao enviar pedido para a cozinha", 500);
            }
        });
    }

    private HttpEntity<KitchenOrderDTO> setRequestEntity(KitchenOrderDTO kitchenOrderDTO){
        return new HttpEntity<KitchenOrderDTO>(kitchenOrderDTO, setHeaders());
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private String setPostUrl(){
        return integrationConfig.getKitchenOrdersUrl();
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

    private String kitchenOrderToJson(KitchenOrderDTO kitchenOrderDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(kitchenOrderDTO);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter KitchenOrderDTO para JSON: {}", e.getMessage());
            throw new KitchenOrderException("Erro ao converter KitchenOrderDTO para JSON", 500);
        }
    }
}
