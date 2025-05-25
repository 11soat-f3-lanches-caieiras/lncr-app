package br.com.tp.lanchescaieiras.commons.adapters.outbounds.integrations;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions.PaymentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerOrderIntegrationImpl implements CustomerOrderIntegration {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderIntegrationImpl.class);
    public final IntegrationConfig integrationConfig;

    public CustomerOrderIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus) {
       String url = integrationConfig.getCustomerOrderUrl() + "/" + customerOrderId + "/updateStatus/" + newStatus;
       HttpHeaders headers = new HttpHeaders();
       headers.set("Content-Type", "application/json");
       HttpEntity<String> request = new HttpEntity<>(null,headers);
       CompletableFuture.runAsync(() -> {
           try {
               log.info("Atualizando status da CustomeOrder " + url);
               new RestTemplate().exchange(url, HttpMethod.PUT,request, String.class);
               // TODO Avaliar porque o método PATCH parou de funcionar nesta integração e PUT funciona.
           } catch (Exception e) {
               log.error(e.toString());
               throw new PaymentException("Erro ao atualizar o status do pedido de cliente: " + customerOrderId, 500);
           }
       });
    }



}
