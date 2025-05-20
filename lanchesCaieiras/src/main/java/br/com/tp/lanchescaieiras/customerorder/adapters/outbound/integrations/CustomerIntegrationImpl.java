package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.CustomerOrderException;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerIntegrationImpl implements CustomerIntegration{

    private final IntegrationConfig integrationConfig;

    public  CustomerIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public CustomerOrderCustomer getCustomerOrderCustomerDetails(Integer customerId) {
        String url = integrationConfig.getCustomersUrl() + "/" + customerId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getCustomerDetails = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        try {
            getCustomerDetails = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                throw new FoodItemException("Cliente id: " + customerId + " não encontrado", 404);
            }
        }
        return jsonToCustomerOrderCustomer(getCustomerDetails.getBody());
    }

    public CustomerOrderCustomer jsonToCustomerOrderCustomer(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            JsonNode _content = jsonNode.get("_content");

            return new CustomerOrderCustomer(
                    _content.get("id").asInt(),
                    _content.get("name").asText());
        }catch (Exception e) {
            throw new CustomerOrderException("Error converting JSON to CustomerOrderCustomer", 500);
        }
    }
}
