package br.com.tp.lanchescaieiras._external.integrations.customerorder;


import br.com.tp.lanchescaieiras._external.commons.utils.IntegrationUtil;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
        String url = getUrl(customerOrderId, newStatus);
        log.info("Atualizando status da CustomeOrder " + getUrl(customerOrderId, newStatus));
        ResponseEntity<String> response = IntegrationUtil.patchForObject(url, null);
        log.info("Resposta da atualização do status do pedido de cliente: " + response);
    }

    private String getUrl(Integer customerOrderId, String newStatus) {
        return integrationConfig.getCustomerOrdersUrl() + "/" + customerOrderId + "/updateStatus/" + newStatus;
    }
}
