package br.com.tp.lanchescaieiras._external.webhooks;

import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lanchescaieiras._core.domain.exceptions.PaymentException;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/webhooks")
public class WebhookMercadoPagoController {

    private final IntegrationConfig integrationConfig;
    private final RestTemplate restTemplate;

    public WebhookMercadoPagoController(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    @PostMapping("/payments/mercadoPago/callback")
    public ResponseEntity<ResponseModel<String>> callback(@RequestParam(name = "id") String id,
                     @RequestParam(name = "topic", defaultValue = "payment") String topic) {
        CompletableFuture.runAsync(() -> {
            HttpEntity<Map<String, Object>> requestEntity = createHttpEntity(id, topic);
            restTemplate.exchange(getUrl(id,topic), HttpMethod.PATCH, requestEntity, new ParameterizedTypeReference<ResponseModel<String>>() {});
        });
        return ResponseEntityModelUtil.Accepted(null);
    }

    private String getUrl(String id, String topic) {
        return integrationConfig.getPaymentsUrl() + "/paymentReceived?id="+ id +"&topic="+topic;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private HttpEntity<Map<String, Object>> createHttpEntity(String id, String topic) {
        return new HttpEntity<>(getHeaders());
    }



}
