package br.com.tp.lanchescaieiras._external.datasources.postgres.payment;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "payment", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"orderId"})
})
public abstract class AbstractJpaPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Integer statusId;
    private Double amount;
    private String paymentProvider;
    private String paymentMethod;
    private String externalPaymentId;
    private LocalDateTime _created;
    private LocalDateTime _updated;

    public AbstractJpaPaymentEntity(Integer id, Integer orderId, Integer status, Double amount, String paymentProvider, String paymentMethod, String externalPaymentId, LocalDateTime _created, LocalDateTime _updated) {
        this.id = id;
        this.orderId = orderId;
        this.statusId = status;
        this.amount = amount;
        this.paymentProvider = paymentProvider;
        this.paymentMethod = paymentMethod;
        this.externalPaymentId = externalPaymentId;
        this._created = _created;
        this._updated = _updated;
    }

    public AbstractJpaPaymentEntity() {
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getExternalPaymentId() {
        return externalPaymentId;
    }

    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }

    public LocalDateTime get_created() {
        return _created;
    }

    public void set_created(LocalDateTime _created) {
        this._created = _created;
    }

    @PrePersist
    public void prePersist() {
        this._created = LocalDateTime.now();
    }


    public LocalDateTime get_updated() {
        return _updated;
    }

    public void set_updated(LocalDateTime _updated) {
        this._updated = _updated;
    }

    @PreUpdate
    public void preUpdate() {
        this._updated = LocalDateTime.now();
    }
}
