package br.com.tp.lncr.external.datasources.postgres.payment.mercadopago;

import br.com.tp.lncr.external.datasources.postgres.payment.AbstractJpaPaymentEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="payment_mercadopago")
public class JpaMercadopagoQrEntity extends AbstractJpaPaymentEntity {
    private String meliId;
    private String qrData;

    public JpaMercadopagoQrEntity() {
        super();
    }

    public JpaMercadopagoQrEntity(Integer id, Integer orderId, Integer status, Double amount, String paymentProvider, String paymentMethod, String externalPaymentId, LocalDateTime _created, LocalDateTime _updated, String meliId, String qrData) {
        super(id, orderId, status, amount, paymentProvider, paymentMethod, externalPaymentId, _created, _updated);
        this.meliId = meliId;
        this.qrData = qrData;
    }


    public String getMeliId() {
        return meliId;
    }

    public void setMeliId(String meliId) {
        this.meliId = meliId;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }
}
