package br.com.tp.lanchescaieiras._external.integrations.payment;

import br.com.tp.lanchescaieiras._core.commons.exceptions.CustomerOrderException;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class PaymentIntegrationImpl implements PaymentIntegration {

    private static final Logger log = LoggerFactory.getLogger(PaymentIntegrationImpl.class);
    private final IntegrationConfig integrationConfig;

    public PaymentIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void createPayment(Integer customerOrderId, Double totalCost) {
        String url = integrationConfig.getPaymentsUrl() + "/charge";
        String createChargeBody = buildPaymentJson(customerOrderId, totalCost);
        log.info("Criando cobrança{}\n{}", createChargeBody);
        RestTemplate restTemplate = new RestTemplate();
        CompletableFuture.runAsync(() -> {
            try {
                log.info("Iniciando envio assíncrono para {}", url);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/json");
                HttpEntity<String> request = new HttpEntity<>(createChargeBody, headers);
                restTemplate.postForObject(url, request, Void.class);
            } catch (Exception e) {
                throw new CustomerOrderException("Erro ao criar cobrança para o pedido", 500);
            }
        });
    }

    private String buildPaymentJson(Integer customerOrderId, Double totalCost) {
        return String.format("{\"orderId\": "+customerOrderId +", \"amount\": "+ totalCost +"}");
    }
}
