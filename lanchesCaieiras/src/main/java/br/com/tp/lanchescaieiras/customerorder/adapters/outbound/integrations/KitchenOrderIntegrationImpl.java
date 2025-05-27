package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class KitchenOrderIntegrationImpl implements KitchenOrderIntegration {

    private static final Logger log = LoggerFactory.getLogger(KitchenOrderIntegrationImpl.class);
    private final IntegrationConfig integrationConfig;

    public KitchenOrderIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void sendKitchenOrder(String kitchenOrder) {

        String url = integrationConfig.getKitchenOrderUrl();
        log.info("Enviando pedido de preparo para cozinha. {}\n{}", kitchenOrder);
        RestTemplate restTemplate = new RestTemplate();
        CompletableFuture.runAsync(() -> {
            try {
                log.info("Iniciando envio assíncrono para {}", url);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");
                HttpEntity<String> request = new HttpEntity<>(kitchenOrder, headers);
                restTemplate.postForObject(url, request, KitchenOrderResponse.class);
            } catch (Exception e) {
                throw new CustomerOrderException("Erro ao enviar pedido para a cozinha", 500);
            }
        });
    }
}
