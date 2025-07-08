package br.com.tp.lanchescaieiras._external.integrations.customerorder;


import br.com.tp.lanchescaieiras._core.domain.exceptions.PaymentException;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CustomerOrderIntegrationImpl implements CustomerOrderIntegration {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderIntegrationImpl.class);
    public final IntegrationConfig integrationConfig;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    public CustomerOrderIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus) {
        // Corpo vazio, pois o método PUT não requer corpo na atualização de status
        executorService.submit(() -> {
            try {
                log.info("Atualizando status da CustomeOrder " + getUrl(customerOrderId, newStatus));
                String response = restTemplate.exchange(createHttpEntity(customerOrderId, newStatus), String.class).getBody();
                log.info("Resposta da atualização do status do pedido de cliente: " + response);

            } catch (Exception e) {
                log.error(e.toString());
                throw new PaymentException("Erro ao atualizar o status do pedido de cliente: " + customerOrderId, 500);
            }
        });
    }

    private String getUrl(Integer customerOrderId, String newStatus) {
        return integrationConfig.getCustomerOrdersUrl() + "/" + customerOrderId + "/updateStatus/" + newStatus;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private RequestEntity<String> createHttpEntity(Integer customerOrderId, String newStatus) {
        RequestEntity<String> request = RequestEntity.
                method(HttpMethod.PATCH, getUrl(customerOrderId, newStatus))
                .headers(getHeaders())
                .body("{}");
        return request;
    }
}
