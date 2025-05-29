package br.com.tp.lanchescaieiras.fooditem.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemInboundHandlerTest {

    @Test
    void testHandleFoodItemExceptionException() {
        String erro = "Erro";
        FoodItemInboundHandler handler = new FoodItemInboundHandler();
        FoodItemException ex = new FoodItemException("Erro", 400);

        ResponseEntity<Object> response = handler.handleFoodItemExceptionException(ex);

        JsonNode responseBody = new ObjectMapper().valueToTree(response.getBody());
        String responseMessage = responseBody.get("_message").asText();
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(responseMessage.contains(erro));
    }
}
