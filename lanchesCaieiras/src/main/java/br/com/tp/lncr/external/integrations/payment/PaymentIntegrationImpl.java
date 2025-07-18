package br.com.tp.lncr.external.integrations.payment;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.external.commons.utils.IntegrationUtil;
import br.com.tp.lncr.external.configs.IntegrationConfig;
import br.com.tp.lncr.external.integrations.IntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentIntegrationImpl implements PaymentIntegration {

    private static final Logger log = LoggerFactory.getLogger(PaymentIntegrationImpl.class);
    private final IntegrationConfig integrationConfig;

    public PaymentIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void cancelPaymentChargeByCustomerOrderId(Integer customerOrderId) {
        String url = integrationConfig.getPaymentsUrl() + "/" + customerOrderId + "/cancel";
        log.info("Cancelando pagamento por ID do pedido. Url:{}", url);
        ResponseEntity<String> payment = IntegrationUtil.patchForObject(url, null);
        log.info("StatusCode {}",payment.getStatusCode());
        log.info("Response:{}", payment.getBody());
    }

    @Override
    public void createPayment(Integer customerOrderId, Double totalCost) {
        String url = integrationConfig.getPaymentsUrl() + "/charge";
        PaymentMercadopagoQrDTO createChargeBody = new PaymentMercadopagoQrDTO(customerOrderId,totalCost);
        log.info("Criando cobrança.");
        log.info("Url:{}", url);
        log.info("Request Body:{}", IntegrationUtil.toJson(createChargeBody) );
        IntegrationUtil.postForObject(url,createChargeBody);
    }

    @Override
    public PaymentMercadopagoQrDTO getPaymentByCustomerOrderId(Integer id) {
        String url = integrationConfig.getPaymentsUrl() + "/customerOrder/" + id;
        return IntegrationUtil.getForObject(url, PaymentMercadopagoQrDTO.class);
    }

    @Override
    public PaymentMercadopagoQrDTO getPaymentByOrderId(Integer customerOrderId) {
        String url = integrationConfig.getPaymentsUrl() + "/customerOrder/" + customerOrderId;
        log.info("Buscando pagamento por ID do pedido. Url:{}", url);
        PaymentMercadopagoQrDTO payment = IntegrationUtil.getForObject(url, PaymentMercadopagoQrDTO.class);
        if (payment == null) {
            throw new IntegrationException("Pagamento não encontrado para o pedido: " + customerOrderId, 404);
        }
        return payment;
    }


}
