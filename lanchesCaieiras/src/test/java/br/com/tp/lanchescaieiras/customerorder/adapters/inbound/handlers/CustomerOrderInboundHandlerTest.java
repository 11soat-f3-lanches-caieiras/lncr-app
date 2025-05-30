package br.com.tp.lanchescaieiras.customerorder.adapters.inbound.handlers;

import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderInboundHandlerTest {

    @Test
    void testHandleCustomerOrderExceptionException() {
        String erro = "Erro";
        CustomerOrderInboundHandler handler = new CustomerOrderInboundHandler();
        CustomerOrderException ex = new CustomerOrderException(erro, 400);

        ResponseEntity<Object> response = handler.handleCustomerOrderExceptionException(ex);
        JsonNode responseBody = new ObjectMapper().valueToTree(response.getBody());
        String responseMessage = responseBody.get("_message").asText();
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(responseMessage.contains(erro));
    }
}
