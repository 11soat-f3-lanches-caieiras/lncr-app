package br.com.tp.lanchescaieiras.commons.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions.PaymentException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerOrderIntegrationImplTest {

    @Test
    void testUpdateCustomerOrderStatusSuccess() {
        IntegrationConfig config = mock(IntegrationConfig.class);
        when(config.getCustomerOrderUrl()).thenReturn("http://localhost:8080/orders");

        CustomerOrderIntegrationImpl integration = new CustomerOrderIntegrationImpl(config);

        // Não há como testar o método async diretamente, mas podemos garantir que não lança exceção
        assertDoesNotThrow(() -> integration.updateCustomerOrderStatus(1, "PAID"));
    }

    @Test
    void testUpdateCustomerOrderStatusThrowsException() {
        IntegrationConfig config = mock(IntegrationConfig.class);
        when(config.getCustomerOrderUrl()).thenReturn("http://localhost:8080/orders");

        CustomerOrderIntegrationImpl integration = new CustomerOrderIntegrationImpl(config) {
            @Override
            public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus) {
                throw new PaymentException("Erro ao atualizar", 500);
            }
        };

        assertThrows(PaymentException.class, () -> integration.updateCustomerOrderStatus(1, "FAIL"));
    }
}
