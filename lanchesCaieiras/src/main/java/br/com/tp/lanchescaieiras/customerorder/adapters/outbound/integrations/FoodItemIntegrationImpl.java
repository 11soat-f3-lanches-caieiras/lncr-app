package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.customerorder.application.mappers.IntegrationMapper;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FoodItemIntegrationImpl implements FoodItemIntegration {

    public final IntegrationConfig integrationConfig;
    public final IntegrationMapper integrationMapper;

    public FoodItemIntegrationImpl(IntegrationConfig integrationConfig, IntegrationMapper integrationMapper) {
        this.integrationConfig = integrationConfig;
        this.integrationMapper = integrationMapper;
    }

    @Override
    public CustomerOrderFoodItem getFoodItemsDetails(Integer foodItemId) {
        String url = integrationConfig.getFoodItemsUrl() + "/" + foodItemId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getFoodItemDetails = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        try {
            getFoodItemDetails = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            if (getFoodItemDetails.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
        }
        return jsonToCustomerOrderFoodItem(getFoodItemDetails.getBody());
    }

    /*@Override
    public KitchenOrderFoodItem getCustomerOrderFoodItemsDetails(Integer kitchenItemId) {
        return integrationMapper.orderToKichen(getFoodItemsDetails(kitchenItemId));
    }*/


    public CustomerOrderFoodItem jsonToCustomerOrderFoodItem(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            JsonNode foodItems = jsonNode.get("_content");

            return new CustomerOrderFoodItem(
                    foodItems.get("id").asInt(),
                    foodItems.get("name").asText(),
                    foodItems.get("description").asText(),
                    foodItems.get("price").asDouble(),
                    null);
        } catch (Exception e) {
            throw new CustomerOrderException("Error converting JSON to CustomerOrderFoodItem", 500);
        }
    }
}
