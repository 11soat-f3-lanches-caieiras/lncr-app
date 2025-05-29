package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.customerorder.application.mappers.IntegrationMapper;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodItemIntegrationImplTest {

    @Test
    void testJsonToCustomerOrderFoodItemSuccess() {
        FoodItemIntegrationImpl integration = new FoodItemIntegrationImpl(mock(IntegrationConfig.class), mock(IntegrationMapper.class));
        String json = "{\"_content\":{\"id\":1,\"name\":\"Coxinha\",\"description\":\"Frango\",\"price\":10.0}}";
        CustomerOrderFoodItem item = integration.jsonToCustomerOrderFoodItem(json);
        assertEquals(1, item.getId());
        assertEquals("Coxinha", item.getName());
        assertEquals("Frango", item.getDescription());
        assertEquals(10.0, item.getPrice());
    }

    @Test
    void testJsonToCustomerOrderFoodItemThrows() {
        FoodItemIntegrationImpl integration = new FoodItemIntegrationImpl(mock(IntegrationConfig.class), mock(IntegrationMapper.class));
        assertThrows(CustomerOrderException.class, () -> integration.jsonToCustomerOrderFoodItem("invalid"));
    }
}
