package br.com.tp.lanchescaieiras._core.domain.payment;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentMercadopagoQR extends Payment{
    private static final String PROVIDER = "mercadopago";
    private static final String METHOD = "qrcode";
    private UUID storeID;
    private String qrData;

    public PaymentMercadopagoQR(Integer id, Integer orderId, String status, Double amount, String externalPaymentId, LocalDateTime _created, LocalDateTime _updated, UUID storeID, String qrData) {
        super(id, orderId, status, amount, externalPaymentId, _created, _updated);
        this.storeID = storeID;
        this.qrData = qrData;
    }

    public PaymentMercadopagoQR(PaymentMercadopagoQrDTO dto) {
        super(
            dto.getId(),
            dto.getOrderId(),
            dto.getStatus(),
            dto.getAmount(),
            dto.getExternalPaymentId(),
            dto.get_created(),
            dto.get_updated()
        );
        this.qrData = dto.getQrData();
        this.storeID = dto.getStoreId() != null ? UUID.fromString(dto.getStoreId()) : null;
    }

    public UUID getStoreID() {
        return storeID;
    }

    public void setStoreID(UUID storeID) {
        this.storeID = storeID;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    @Override
    public String getPaymentProvider() {
        return PROVIDER;
    }

    @Override
    public String getPaymentMethod() {
        return METHOD;
    }
}
