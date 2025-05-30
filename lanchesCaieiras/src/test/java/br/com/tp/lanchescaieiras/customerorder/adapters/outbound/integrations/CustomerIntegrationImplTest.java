package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerIntegrationImplTest {

    @Test
    void testJsonToCustomerOrderCustomerSuccess() {
        CustomerIntegrationImpl integration = new CustomerIntegrationImpl(mock(IntegrationConfig.class));
        String json = "{\"_content\":{\"id\":1,\"name\":\"João\"}}";
        CustomerOrderCustomer customer = integration.jsonToCustomerOrderCustomer(json);
        assertEquals(1, customer.getId());
        assertEquals("João", customer.getName());
    }

    @Test
    void testJsonToCustomerOrderCustomerThrows() {
        CustomerIntegrationImpl integration = new CustomerIntegrationImpl(mock(IntegrationConfig.class));
        assertThrows(CustomerOrderException.class, () -> integration.jsonToCustomerOrderCustomer("invalid"));
    }

    @Test
    void testGetCustomerOrderCustomerDetailsNotFound() {
        IntegrationConfig config = mock(IntegrationConfig.class);
        when(config.getCustomersUrl()).thenReturn("http://localhost/customers");
        CustomerIntegrationImpl integration = new CustomerIntegrationImpl(config);

        // Simula HttpClientErrorException.NotFound
        RestTemplate restTemplate = mock(RestTemplate.class);
        HttpClientErrorException notFound = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Not Found", null, null, null);
        try (var mocked = Mockito.mockStatic(RestTemplate.class, Mockito.CALLS_REAL_METHODS)) {
            when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(notFound);
            assertThrows(CustomerOrderException.class, () -> integration.getCustomerOrderCustomerDetails(99));
        }
    }
}
