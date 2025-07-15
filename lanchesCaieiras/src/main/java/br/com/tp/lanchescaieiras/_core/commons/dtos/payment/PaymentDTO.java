package br.com.tp.lanchescaieiras._core.commons.dtos.payment;

import java.time.LocalDateTime;

public abstract class PaymentDTO {
    private Integer id;
    private Integer orderId;
    private String status;
    private Double amount;
    private String paymentProvider;
    private String paymentMethod;
    private String externalPaymentId;
    private LocalDateTime _created;
    private LocalDateTime _updated;


    public PaymentDTO() {}

    public PaymentDTO(Integer orderId, Double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public PaymentDTO(Integer id, Integer orderId, String status, Double amount, String paymentProvider, String paymentMethod, LocalDateTime _created, LocalDateTime _updated, String externalPaymentId) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
        this.paymentProvider = paymentProvider;
        this.paymentMethod = paymentMethod;
        this._created = _created;
        this._updated = _updated;
        this.externalPaymentId = externalPaymentId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getPaymentProvider() { return paymentProvider; }
    public void setPaymentProvider(String paymentProvider) { this.paymentProvider = paymentProvider; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public LocalDateTime get_created() { return _created; }
    public void set_created(LocalDateTime _created) { this._created = _created; }
    public LocalDateTime get_updated() { return _updated; }
    public void set_updated(LocalDateTime _updated) { this._updated = _updated; }
    public String getExternalPaymentId() { return externalPaymentId; }
    public void setExternalPaymentId(String externalPaymentId) { this.externalPaymentId = externalPaymentId; }
}

