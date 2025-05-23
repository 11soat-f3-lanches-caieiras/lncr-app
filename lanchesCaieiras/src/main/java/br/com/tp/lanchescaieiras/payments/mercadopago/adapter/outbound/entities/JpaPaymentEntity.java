package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "payment")
public class JpaPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Double amount;
    private UUID storeOrderId;
    private String qrData;
    private Integer statusId;

    public JpaPaymentEntity(Integer id, Integer orderId, Double amount, UUID storeOrderId, String qrData, Integer statusId) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.storeOrderId = storeOrderId;
        this.qrData = qrData;
        this.statusId = statusId;
    }

    public JpaPaymentEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
