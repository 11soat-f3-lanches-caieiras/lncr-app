package br.com.tp.lncr.core.commons.dtos.payment;

import java.time.LocalDateTime;

public class PaymentMercadopagoQrDTO extends PaymentDTO {
    private String qrData;
    private String meliId;

    public PaymentMercadopagoQrDTO() {
        super();
    }

    public PaymentMercadopagoQrDTO(String qrData, String meliId) {
        this.qrData = qrData;
        this.meliId = meliId;
    }

    public PaymentMercadopagoQrDTO(Integer orderId, Double amount) {
        super(orderId, amount);
    }

    public PaymentMercadopagoQrDTO(Integer id, Integer orderId, String status, Double amount, String paymentProvider, String paymentMethod, LocalDateTime _created, LocalDateTime _updated, String externalPaymentId, String qrData, String meliId) {
        super(id, orderId, status, amount, paymentProvider, paymentMethod, _created, _updated, externalPaymentId);
        this.qrData = qrData;
        this.meliId = meliId;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public String getMeliId() {
        return meliId;
    }

    public void setMeliId(String meliId) {
        this.meliId = meliId;
    }
}
