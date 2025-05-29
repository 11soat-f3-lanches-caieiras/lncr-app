package br.com.tp.lanchescaieiras.kitchenorder.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.exceptions.KitchenOrderException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class KitchenOrderInboundHandlerTest {

    @Test
    void testHandleKitchenOrderException() {
        String erro = "Erro";
        KitchenOrderInboundHandler handler = new KitchenOrderInboundHandler();
        KitchenOrderException ex = new KitchenOrderException("Erro", 400);
        ResponseEntity<Object> response = handler.handleKitchenOrderException(ex);

        JsonNode responseBody = new ObjectMapper().valueToTree(response.getBody());
        String responseMessage = responseBody.get("_message").asText();
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(responseMessage.contains(erro));
    }
}
