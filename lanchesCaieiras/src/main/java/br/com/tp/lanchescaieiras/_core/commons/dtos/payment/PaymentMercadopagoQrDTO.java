package br.com.tp.lanchescaieiras._core.commons.dtos.payment;

import java.time.LocalDateTime;

public class PaymentMercadopagoQrDTO extends PaymentDTO {
    private String qrData;
    private String storeId;

    public PaymentMercadopagoQrDTO() {
        super();
    }

    public PaymentMercadopagoQrDTO(String qrData, String storeId) {
        this.qrData = qrData;
        this.storeId = storeId;
    }

    public PaymentMercadopagoQrDTO(Integer orderId, Double amount) {
        super(orderId, amount);
    }

    public PaymentMercadopagoQrDTO(Integer id, Integer orderId, String status, Double amount, String paymentProvider, String paymentMethod, LocalDateTime _created, LocalDateTime _updated, String externalPaymentId, String qrData, String storeId) {
        super(id, orderId, status, amount, paymentProvider, paymentMethod, _created, _updated, externalPaymentId);
        this.qrData = qrData;
        this.storeId = storeId;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
