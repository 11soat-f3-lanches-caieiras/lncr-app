package br.com.tp.lanchescaieiras._core.domain.payment;

import br.com.tp.lanchescaieiras._core.commons.enums.PaymentStatus;
import br.com.tp.lanchescaieiras._core.commons.interfaces.SortedByStatusCreated;

import java.time.LocalDateTime;

public abstract class Payment implements SortedByStatusCreated {
    private Integer id;
    private Integer orderId;
    private String status;
    private Double amount;
    private String externalPaymentId;
    private LocalDateTime _created;
    private LocalDateTime _updated;


    public Payment(Integer id, Integer orderId, String status, Double amount, String externalPaymentId, LocalDateTime _created, LocalDateTime _updated) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
        this.externalPaymentId = externalPaymentId;
        this._created = _created;
        this._updated = _updated;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = PaymentStatus.fromDescription(status).getDescription();
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public LocalDateTime get_created() {
        return _created;
    }
    public void set_created(LocalDateTime _created) {
        this._created = _created;
    }
    public LocalDateTime get_updated() {
        return _updated;
    }
    public void set_updated(LocalDateTime _updated) {
        this._updated = _updated;
    }
    public String getExternalPaymentId() {
        return externalPaymentId;
    }
    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }
    public abstract String getPaymentProvider();
    public abstract String getPaymentMethod();

    }
