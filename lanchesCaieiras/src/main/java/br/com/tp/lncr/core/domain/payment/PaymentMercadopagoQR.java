package br.com.tp.lncr.core.domain.payment;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;

import java.time.LocalDateTime;

public class PaymentMercadopagoQR extends Payment{
    private static final String PROVIDER = "mercadopago";
    private static final String METHOD = "qrcode";
    private String meliId;
    private String qrData;

    public PaymentMercadopagoQR(Integer id, Integer orderId, String status, Double amount, String externalPaymentId, LocalDateTime _created, LocalDateTime _updated, String meliId, String qrData) {
        super(id, orderId, status, amount, externalPaymentId, _created, _updated);
        this.meliId = meliId;
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
        this.meliId = dto.getMeliId();
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

    @Override
    public String getPaymentProvider() {
        return PROVIDER;
    }

    @Override
    public String getPaymentMethod() {
        return METHOD;
    }
}
