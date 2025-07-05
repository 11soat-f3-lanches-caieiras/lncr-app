package br.com.tp.lanchescaieiras._external.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
}
