package br.com.tp.lanchescaieiras.payments.mercadopago.applications.mappers;

import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities.JpaPaymentEntity;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.Payment;
import br.com.tp.lanchescaieiras.payments.mercadopago.domain.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = PaymentStatus.class)
public interface PaymentMapper {
    @Mappings({
            @Mapping(target = "id", source = "jpaPaymentEntity.id"),
            @Mapping(target = "orderId", source = "jpaPaymentEntity.orderId"),
            @Mapping(target = "amount", source = "jpaPaymentEntity.amount"),
            @Mapping(target = "storeOrderId", source = "jpaPaymentEntity.storeOrderId"),
            @Mapping(target = "qrData", source = "jpaPaymentEntity.qrData"),
            @Mapping(target = "status", expression= "java(PaymentStatus.fromId(jpaPaymentEntity.getStatusId()).getDescription())"),
            @Mapping(target = "paymentId", source = "jpaPaymentEntity.paymentId")
    })
    Payment jpaToDomain(JpaPaymentEntity jpaPaymentEntity);

    @Mappings({
            @Mapping(target = "id", source = "payment.id"),
            @Mapping(target = "orderId", source = "payment.orderId"),
            @Mapping(target = "amount", source = "payment.amount"),
            @Mapping(target = "storeOrderId", source = "payment.storeOrderId"),
            @Mapping(target = "qrData", source = "payment.qrData"),
            @Mapping(target = "statusId", expression = "java(PaymentStatus.fromDescription(payment.getStatus()).getId())"),
            @Mapping(target = "paymentId", source = "payment.paymentId")
    })
    JpaPaymentEntity domainToJpa(Payment payment);

}
