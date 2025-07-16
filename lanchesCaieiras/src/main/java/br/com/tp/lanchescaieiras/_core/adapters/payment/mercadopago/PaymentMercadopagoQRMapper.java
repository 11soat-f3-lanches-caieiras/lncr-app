package br.com.tp.lanchescaieiras._core.adapters.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;

public class PaymentMercadopagoQRMapper {

    public PaymentMercadopagoQRMapper() {
    }

    public PaymentMercadopagoQrDTO paymentMercadopagoQrToDTO(PaymentMercadopagoQR entity) {
        if (entity == null) return null;
        return new PaymentMercadopagoQrDTO(
            entity.getId(),
            entity.getOrderId(),
            entity.getStatus(),
            entity.getAmount(),
            entity.getPaymentProvider(),
            entity.getPaymentMethod(),
            entity.get_created(),
            entity.get_updated(),
            entity.getExternalPaymentId(),
            entity.getQrData(),
            entity.getMeliId() != null ? entity.getMeliId().toString() : null
        );
    }

    public PaymentMercadopagoQR paymentMercadopagoQrToDomain(PaymentMercadopagoQrDTO dto) {
        if (dto == null) return null;
        return new PaymentMercadopagoQR(dto);
    }
}

