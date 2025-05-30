package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KitchenOrderIntegrationImplTest {

    @Test
    void testSendKitchenOrderNoException() {
        IntegrationConfig config = mock(IntegrationConfig.class);
        when(config.getKitchenOrderUrl()).thenReturn("http://localhost/kitchen");
        KitchenOrderIntegrationImpl integration = new KitchenOrderIntegrationImpl(config);

        // Não há como testar o envio assíncrono diretamente, mas pode-se garantir que não lança exceção
        assertDoesNotThrow(() -> integration.sendKitchenOrder("{\"order\":1}"));
    }
}
