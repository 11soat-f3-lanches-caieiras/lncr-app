package br.com.tp.lanchescaieiras._external.datasources.postgres.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.enums.PaymentStatus;

public class JpaPaymentMercadopagoQRMapper {
    public  PaymentMercadopagoQrDTO jpaMercadopagoQrToDTO(JpaMercadopagoQrEntity entity) {
        if (entity == null) return null;
        return new PaymentMercadopagoQrDTO(
                entity.getId(),
                entity.getOrderId(),
                PaymentStatus.fromId(entity.getStatusId()).getDescription(),
                entity.getAmount(),
                entity.getPaymentProvider(),
                entity.getPaymentMethod(),
                entity.get_created(),
                entity.get_updated(),
                entity.getExternalPaymentId(),
                entity.getQrData(),
                entity.getStoreId()
        );
    }

    public JpaMercadopagoQrEntity mercadopagoQrDtoToJpa(PaymentMercadopagoQrDTO dto) {
        if (dto == null) return null;
        return new JpaMercadopagoQrEntity(
                dto.getId(),
                dto.getOrderId(),
                PaymentStatus.fromDescription(dto.getStatus()).getId(),
                dto.getAmount(),
                dto.getPaymentProvider(),
                dto.getPaymentMethod(),
                dto.getExternalPaymentId(),
                dto.get_created(),
                dto.get_updated(),
                dto.getStoreId(),
                dto.getQrData());
    }
}

