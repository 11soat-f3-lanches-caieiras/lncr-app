package br.com.tp.lanchescaieiras._external.integrations.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import br.com.tp.lanchescaieiras._external.integrations.IntegrationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FoodItemIntegrationImpl implements FoodItemIntegration {

    public final IntegrationConfig integrationConfig;

    public FoodItemIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public CustomerOrderFoodItemDTO getFoodItemDetailsFromCustomerOrder(Integer foodItemId) {
        FoodItemDTO foodItemDTO = getFoodItemsDetails(foodItemId);
        return new CustomerOrderFoodItemDTO(foodItemDTO.getId(),null,foodItemDTO.getName(),foodItemDTO.getDescription(), foodItemDTO.getPrice(),null);
    }

    private FoodItemDTO getFoodItemsDetails(Integer foodItemId) {
        String url = integrationConfig.getFoodItemsUrl() + "/" + foodItemId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getFoodItemDetails = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        try {
            getFoodItemDetails = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            if (getFoodItemDetails.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw  new IntegrationException("Erro ao obter os dados do items de alimentação id: " + foodItemId,500);
            }
        }
        return getFoodItemContent(getFoodItemDetails.getBody());
    }

    private FoodItemDTO getFoodItemContent(String body){

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(body);
            JsonNode contentNode = root.path("_content");
            return mapper.treeToValue(contentNode, FoodItemDTO.class);
        } catch (JsonProcessingException e) {
            throw new IntegrationException("Erro ao mapear os dados de items de alimentação",500);
        }
    }







}
