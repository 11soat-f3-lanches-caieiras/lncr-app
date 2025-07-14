package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.payment.mercadopago.PaymentMercadoPagoQrControllerImpl;
import br.com.tp.lanchescaieiras._core.adapters.payment.mercadopago.PaymentMercadopagoQRMapper;
import br.com.tp.lanchescaieiras._external.dataproxy.PaymentMercadoPagoQrDataProxy;
import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.mercadopago.JpaMercadoPagoQrPostgresRepositoryImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.mercadopago.JpaPaymentMercadopagoQRMapper;
import br.com.tp.lanchescaieiras._external.integrations.customerorder.CustomerOrderIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.notifcation.NotificationIntegraionImpl;
import br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago.MercadoPagoIntegrationImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lncr.mercado-pago")
public class MercadoPagoConfig {
    public String locationPrefix;
    public String callbackUrl;
    public String chargeUrl;
    public String paymentUrl;
    public String accessToken;
    public String userId;
    public String posId;
    public Boolean mercadoPagoMock;
    public Integer mercadoPagoMockCustomerOrderId;

    public String getLocationPrefix() {
        return locationPrefix;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getChargeUrl() {
        return chargeUrl;
    }

    public void setChargeUrl(String chargeUrl) {
        this.chargeUrl = chargeUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public Boolean getMercadoPagoMock() {
        return mercadoPagoMock;
    }

    public void setMercadoPagoMock(Boolean mercadoPagoMock) {
        this.mercadoPagoMock = mercadoPagoMock;
    }

    public Integer getMercadoPagoMockCustomerOrderId() {
        return mercadoPagoMockCustomerOrderId;
    }

    public void setMercadoPagoMockCustomerOrderId(Integer mercadoPagoMockCustomerOrderId) {
        this.mercadoPagoMockCustomerOrderId = mercadoPagoMockCustomerOrderId;
    }
    @Bean
    public PaymentMercadopagoQRMapper paymentMercadopagoQRMapper(){
        return new PaymentMercadopagoQRMapper();
    }

    @Bean
    public PaymentMercadoPagoQrControllerImpl paymentMercadoPagoQrController(PaymentMercadopagoQRMapper paymentMercadopagoQRMapper){
        return new PaymentMercadoPagoQrControllerImpl(paymentMercadopagoQRMapper);
    }

    @Bean
    public PaymentMercadoPagoQrDataProxy paymentMercadoPagoQrDataProxy(JpaMercadoPagoQrPostgresRepositoryImpl jpaMercadoPagoQrPostgresDatabase, MercadoPagoIntegrationImpl mercadoPagoIntegration, CustomerOrderIntegrationImpl customerOrderIntegration, NotificationIntegraionImpl notificationIntegration){
        return new PaymentMercadoPagoQrDataProxy(jpaMercadoPagoQrPostgresDatabase,mercadoPagoIntegration,customerOrderIntegration, notificationIntegration);
    }

    @Bean
    public JpaPaymentMercadopagoQRMapper jpaPaymentMercadopagoQRMapper(){
        return new JpaPaymentMercadopagoQRMapper();
    }



}
