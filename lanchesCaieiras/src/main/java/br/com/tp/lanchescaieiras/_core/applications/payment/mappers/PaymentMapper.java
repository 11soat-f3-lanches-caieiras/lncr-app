package br.com.tp.lanchescaieiras._core.applications.payment.mappers;

import br.com.tp.lanchescaieiras._core.domain.payment.Payment;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentStatus;
import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.JpaPaymentEntity;
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
            @Mapping(target = "status", expression = "java(PaymentStatus.fromId(jpaPaymentEntity.getStatusId()).getDescription())"),
    })
    Payment jpaToDomain(JpaPaymentEntity jpaPaymentEntity);

    @Mappings({
            @Mapping(target = "id", source = "payment.id"),
            @Mapping(target = "orderId", source = "payment.orderId"),
            @Mapping(target = "amount", source = "payment.amount"),
            @Mapping(target = "storeOrderId", source = "payment.storeOrderId"),
            @Mapping(target = "qrData", source = "payment.qrData"),
            @Mapping(target = "statusId", expression = "java(PaymentStatus.fromDescription(payment.getStatus()).getId())"),
    })
    JpaPaymentEntity domainToJpa(Payment payment);

}
