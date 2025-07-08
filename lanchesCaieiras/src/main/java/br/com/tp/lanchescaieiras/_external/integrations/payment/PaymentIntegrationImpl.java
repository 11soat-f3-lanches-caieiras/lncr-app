package br.com.tp.lanchescaieiras._external.integrations.payment;

import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentIntegrationImpl implements PaymentIntegration {

    private static final Logger log = LoggerFactory.getLogger(PaymentIntegrationImpl.class);
    private final IntegrationConfig integrationConfig;

    public PaymentIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void createPayment(String payment) {
        String url = integrationConfig.getPaymentsUrl() + "/charge";
        log.info("Enviando pedido de preparo para cozinha. {}\n{}", payment);
        RestTemplate restTemplate = new RestTemplate();
        CompletableFuture.runAsync(() -> {
            try {
                log.info("Iniciando envio assíncrono para {}", url);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");
                HttpEntity<String> request = new HttpEntity<>(payment, headers);
                restTemplate.postForObject(url, request, Void.class);
            } catch (Exception e) {
                throw new CustomerOrderException("Erro ao criar cobrança para o pedido", 500);
            }
        });


    }
}
