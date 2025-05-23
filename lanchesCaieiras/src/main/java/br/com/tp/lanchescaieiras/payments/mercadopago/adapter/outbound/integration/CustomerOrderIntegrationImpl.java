package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.IntegrationConfig;
import br.com.tp.lanchescaieiras.payments.mercadopago.infraestructure.exceptions.PaymentException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class CustomerOrderIntegrationImpl implements CustomerOrderIntegration {

    public final IntegrationConfig integrationConfig;

    public CustomerOrderIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus) {
        String url = integrationConfig.getCustomerOrdersUrl() + "/" + customerOrderId + "/updateStatus/" + newStatus;
        CompletableFuture.runAsync(() -> {
            try {
                new RestTemplate().patchForObject(url, null, String.class);
            } catch (Exception e) {
                throw new PaymentException("Erro ao atualizar o status do pedido de cliente: " + customerOrderId, 500);
            }
        });
    }



}
