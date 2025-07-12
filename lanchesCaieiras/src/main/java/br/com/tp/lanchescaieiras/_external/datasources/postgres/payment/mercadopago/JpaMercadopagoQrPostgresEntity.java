package br.com.tp.lanchescaieiras._external.datasources.postgres.payment.mercadopago;

import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.JpaPaymentPostgresEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="payment_mercadopago")
public class JpaMercadopagoQrPostgresEntity extends JpaPaymentPostgresEntity {
    private String storeId;
    private String qrData;

    public JpaMercadopagoQrPostgresEntity() {
        super();
    }

    public JpaMercadopagoQrPostgresEntity(Integer id, Integer orderId, Integer status, Double amount, String paymentProvider, String paymentMethod, String externalPaymentId, LocalDateTime _created, LocalDateTime _updated, String storeId, String qrData) {
        super(id, orderId, status, amount, paymentProvider, paymentMethod, externalPaymentId, _created, _updated);
        this.storeId = storeId;
        this.qrData = qrData;
    }


    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }
}
