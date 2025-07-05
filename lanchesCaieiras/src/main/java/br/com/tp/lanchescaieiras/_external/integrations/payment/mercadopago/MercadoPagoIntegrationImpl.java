package br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.domain.exceptions.PaymentException;
import br.com.tp.lanchescaieiras._core.domain.payment.Payment;
import br.com.tp.lanchescaieiras._external.configs.MercadoPagoConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class MercadoPagoIntegrationImpl implements MercadoPagoIntegration {

    private static final Logger log = LoggerFactory.getLogger(MercadoPagoIntegrationImpl.class);
    public final MercadoPagoConfig mercadoPagoConfig;

    public MercadoPagoIntegrationImpl(MercadoPagoConfig mercadoPagoConfig) {
        this.mercadoPagoConfig = mercadoPagoConfig;
    }

    @Override
    public Payment createQRCode(Payment payment) {
        ResponseEntity<String> createdQrBody = callMercadoPagoQrCode(payment);
        payment = parseResponseToPayment(createdQrBody.getBody(), payment);
        return payment;
    }

    @Override
    public Integer getPaymentId(String paymentId) {
        return callMercadoPagoGetPaymentId(paymentId);
    }

    private Integer callMercadoPagoGetPaymentId(String paymentId) {
        String url = mercadoPagoConfig.getPaymentUrl() + "/" + paymentId;
        HttpHeaders headers = setAuthorizationBearer(mercadoPagoConfig.getAccessToken());
        log.info("Buscando informações do pagamento no mercado pago.\n{}", url);
        if (mercadoPagoConfig.getMercadoPagoMock() == true) {
            return mercadoPagoConfig.mercadoPagoMockCustomerOrderId;

        } else {
            try {
                ResponseEntity<String> paymentResponse = new RestTemplate().exchange(url, HttpMethod.GET, createHttpEntity("", headers), String.class);
                return parsePaymentId(paymentResponse.getBody());
            } catch (Exception ex) {
                throw new PaymentException("Erro ao obter informações de pagamento no Mercado Pago", 500);
            }
        }
    }

    private Integer parsePaymentId(String paymentResponse) {
        log.info("Obtendo o id do pedido no pagamento realizado");
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(paymentResponse);
            if (jsonNode.has("status") && jsonNode.get("status").asText().equals("approved") &&
                    jsonNode.has("status_detail") && jsonNode.get("status_detail").asText().equals("accredited")) {
                if (jsonNode.has("external_reference")) {
                    return jsonNode.get("external_reference").asInt();
                }
            }
            return null;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error parsing Mercado Pago response", e);
        }
    }

    private ResponseEntity<String> callMercadoPagoQrCode(Payment payment) {
        HttpHeaders headers = setAuthorizationBearer(mercadoPagoConfig.getAccessToken());
        String request = createQrCodeRequestBody(payment);
        log.info("Solicitando via MercadoPago nova cobrança de {} do pedido {}\n{}", payment.getAmount(), payment.getOrderId(), request);
        return new RestTemplate().exchange(mercadoPagoConfig.chargeUrl, HttpMethod.POST, new HttpEntity<>(request, headers), String.class);
    }

    private HttpHeaders setAuthorizationBearer(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private HttpEntity<String> createHttpEntity(String request, HttpHeaders headers) {
        return new HttpEntity<>(request, headers);
    }

    private String createQrCodeRequestBody(Payment payment) {
        return "{\n" +
                "    \"external_reference\": \"" + payment.getId() + "\",\n" +
                "    \"title\": \"Pedido id " + payment.getOrderId() + "\",\n" +
                "    \"description\": \"Novo Pedido Lanches Caieiras\",\n" +
                "    \"notification_url\": \"" + mercadoPagoConfig.callbackUrl + "\",\n" +
                "    \"total_amount\": " + payment.getAmount() + ",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"title\": \"Pedido Lanches Caieiras " + payment.getId() + "\",\n" +
                "            \"unit_price\": " + payment.getAmount() + ",\n" +
                "            \"quantity\": 1,\n" +
                "            \"unit_measure\": \"unit\",\n" +
                "            \"total_amount\": " + payment.getAmount() + "\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private Payment parseResponseToPayment(String response, Payment payment) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(response);
            payment.setStoreOrderId(UUID.fromString(jsonNode.get("in_store_order_id").asText()));
            payment.setQrData(jsonNode.get("qr_data").asText());
            return payment;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error parsing Mercado Pago response", e);
        }
    }
}
