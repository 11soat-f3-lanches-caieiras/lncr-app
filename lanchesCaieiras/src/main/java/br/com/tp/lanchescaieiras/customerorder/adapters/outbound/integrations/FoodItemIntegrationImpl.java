package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.CustomerOrderException;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class FoodItemIntegrationImpl implements  FoodItemIntegration {


    public final IntegrationConfig integrationConfig;

    public FoodItemIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public CustomerOrderFoodItem getFoodItemsDetails(Integer foodItemId) {
        String url = integrationConfig.getFoodItemsUrl() + "/" + foodItemId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getFoodItemDetails = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        try {
            getFoodItemDetails = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                throw new FoodItemException("Item do pedido id: " + foodItemId + " não encontrado", 404);
            }
        }
        return jsonToCustomerOrderFoodItem(getFoodItemDetails.getBody());
    }

    public CustomerOrderFoodItem jsonToCustomerOrderFoodItem(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            JsonNode foodItems = jsonNode.get("foodItem");

            return new CustomerOrderFoodItem(
                    foodItems.get("id").asInt(),
                    foodItems.get("name").asText(),
                    foodItems.get("description").asText(),
                    foodItems.get("price").asDouble(),
                    null);
        }catch (Exception e) {
            throw new CustomerOrderException("Error converting JSON to CustomerOrderFoodItem", 500);
        }
    }
}
