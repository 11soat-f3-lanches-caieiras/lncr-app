package br.com.tp.lanchescaieiras.payments.mercadopago.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    private String id;
    private Integer orderId;
    private Double amount;
    private UUID storeOrderId;
    private String qrData;
    private String status;
    private String paymentId;

    public Payment(String id, Integer orderId, Double amount, UUID storeOrderId, String qrData, String status, String paymentId) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.storeOrderId = storeOrderId;
        this.qrData = qrData;
        this.status = status;
        this.paymentId = paymentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public UUID getStoreOrderId() {
        return storeOrderId;
    }

    public void setStoreOrderId(UUID storeOrderId) {
        this.storeOrderId = storeOrderId;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
