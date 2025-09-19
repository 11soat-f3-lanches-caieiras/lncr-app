package br.com.tp.lncr.app.configs;

import br.com.tp.lncr.app.dataproxy.PaymentMercadoPagoQrDataProxy;
import br.com.tp.lncr.app.datasources.postgres.payment.mercadopago.JpaMercadoPagoQrRepositoryImpl;
import br.com.tp.lncr.app.datasources.postgres.payment.mercadopago.JpaPaymentMercadopagoQRMapper;
import br.com.tp.lncr.app.integrations.customerorder.CustomerOrderIntegrationImpl;
import br.com.tp.lncr.app.integrations.notifcation.NotificationIntegraionImpl;
import br.com.tp.lncr.app.integrations.payment.mercadopago.MercadoPagoIntegrationImpl;
import br.com.tp.lncr.core.adapters.payment.mercadopago.PaymentMercadoPagoQrControllerImpl;
import br.com.tp.lncr.core.adapters.payment.mercadopago.PaymentMercadopagoQRMapper;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentController;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lncr.mercado-pago")
public class MercadoPagoConfig {
    public String locationPrefix;
    public String oAuthUrl;
    public String ordersUrl;
    public String clientId;
    public String secretId;
    public String posId;
    public String expirationTime;

    public String getLocationPrefix() {
        return locationPrefix;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    public String getoAuthUrl() {
        return oAuthUrl;
    }

    public void setoAuthUrl(String oAuthUrl) {
        this.oAuthUrl = oAuthUrl;
    }

    public String getOrdersUrl() {
        return ordersUrl;
    }

    public void setOrdersUrl(String ordersUrl) {
        this.ordersUrl = ordersUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Bean
    public PaymentMercadopagoQRMapper paymentMercadopagoQRMapper(){
        return new PaymentMercadopagoQRMapper();
    }

    @Bean
    public PaymentController paymentMercadoPagoQrController(PaymentMercadopagoQRMapper paymentMercadopagoQRMapper){
        return new PaymentMercadoPagoQrControllerImpl(paymentMercadopagoQRMapper);
    }

    @Bean
    public PaymentMercadoPagoQrDataProxy paymentMercadoPagoQrDataProxy(JpaMercadoPagoQrRepositoryImpl jpaMercadoPagoQrPostgresDatabase, MercadoPagoIntegrationImpl mercadoPagoIntegration, CustomerOrderIntegrationImpl customerOrderIntegration, NotificationIntegraionImpl notificationIntegration){
        return new PaymentMercadoPagoQrDataProxy(jpaMercadoPagoQrPostgresDatabase,mercadoPagoIntegration,customerOrderIntegration, notificationIntegration);
    }

    @Bean
    public JpaPaymentMercadopagoQRMapper jpaPaymentMercadopagoQRMapper(){
        return new JpaPaymentMercadopagoQRMapper();
    }



}
