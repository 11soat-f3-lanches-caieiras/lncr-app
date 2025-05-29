package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.inbound.handlers;

import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions.PaymentException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInboundHandlerTest {

    @Test
    void testHandleKitchenOrderException() {
        String erro = "Erro";
        PaymentInboundHandler handler = new PaymentInboundHandler();
        PaymentException ex = new PaymentException("Erro", 400);
        ResponseEntity<Object> response = handler.handleKitchenOrderException(ex);

        JsonNode responseBody = new ObjectMapper().valueToTree(response.getBody());
        String responseMessage = responseBody.get("_message").asText();
        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(responseMessage.contains(erro));
    }
}
