package br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._external.configs.MercadoPagoConfig;
import br.com.tp.lanchescaieiras._external.integrations.IntegrationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MercadoPagoIntegrationImpl implements MercadoPagoIntegration {

    private static final Logger log = LoggerFactory.getLogger(MercadoPagoIntegrationImpl.class);
    public final MercadoPagoConfig mercadoPagoConfig;

    public MercadoPagoIntegrationImpl(MercadoPagoConfig mercadoPagoConfig) {
        this.mercadoPagoConfig = mercadoPagoConfig;
    }

    @Override
    public PaymentMercadopagoQrDTO createQRCode(PaymentMercadopagoQrDTO paymentMercadopagoQrDTO) {
        ResponseEntity<String> createdQrBody = callMercadoPagoQrCode(paymentMercadopagoQrDTO);
        paymentMercadopagoQrDTO = parseResponseToPayment(createdQrBody.getBody(), paymentMercadopagoQrDTO);
        return paymentMercadopagoQrDTO;
    }

    @Override
    public Integer getPaymentId(String paymentId) {
        return callMercadoPagoGetPaymentId(paymentId);
    }

    private Integer callMercadoPagoGetPaymentId(String paymentId) {
        String url = mercadoPagoConfig.getPaymentUrl() + "/" + paymentId;
        HttpHeaders headers = setAuthorizationBearer(mercadoPagoConfig.getAccessToken());
        log.info("Buscando informações do pagamento no mercado pago.\n{}", url);
            try {
                ResponseEntity<String> paymentResponse = new RestTemplate().exchange(url, HttpMethod.GET, createHttpEntity("", headers), String.class);
                return parsePaymentId(paymentResponse.getBody());
            } catch (Exception ex) {
                throw new IntegrationException("Erro ao obter informações de pagamento no Mercado Pago", 500);
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

    private ResponseEntity<String> callMercadoPagoQrCode(PaymentMercadopagoQrDTO paymentMercadopagoQrDTO) {
        HttpHeaders headers = setAuthorizationBearer(mercadoPagoConfig.getAccessToken());
        String request = createQrCodeRequestBody(paymentMercadopagoQrDTO);
        log.info("Solicitando via MercadoPago nova cobrança de {} do pedido {}\n{}", paymentMercadopagoQrDTO.getAmount(), paymentMercadopagoQrDTO.getOrderId(), request);
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

    private String createQrCodeRequestBody(PaymentMercadopagoQrDTO paymentMercadopagoQrDTO) {
        return "{\n" +
                "    \"external_reference\": \"" + paymentMercadopagoQrDTO.getOrderId() + "\",\n" +
                "    \"title\": \"Pedido id " + paymentMercadopagoQrDTO.getOrderId() + "\",\n" +
                "    \"description\": \"Novo Pedido Lanches Caieiras\",\n" +
                "    \"notification_url\": \"" + mercadoPagoConfig.callbackUrl + "\",\n" +
                "    \"total_amount\": " + paymentMercadopagoQrDTO.getAmount() + ",\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"title\": \"Pedido Lanches Caieiras " + paymentMercadopagoQrDTO.getOrderId() + "\",\n" +
                "            \"unit_price\": " + paymentMercadopagoQrDTO.getAmount() + ",\n" +
                "            \"quantity\": 1,\n" +
                "            \"unit_measure\": \"unit\",\n" +
                "            \"total_amount\": " + paymentMercadopagoQrDTO.getAmount() + "\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private PaymentMercadopagoQrDTO parseResponseToPayment(String response, PaymentMercadopagoQrDTO paymentMercadopagoQrDTO) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(response);
            paymentMercadopagoQrDTO.setStoreId(jsonNode.get("in_store_order_id").asText());
            paymentMercadopagoQrDTO.setQrData(jsonNode.get("qr_data").asText());
            return paymentMercadopagoQrDTO;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error parsing Mercado Pago response", e);
        }
    }
}
