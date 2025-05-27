package br.com.tp.lanchescaieiras.commons.applications.mappers;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.entity.JpaNotificationEntity;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mappings({
            @Mapping(target = "source", expression = "java(new Object())"),
            @Mapping(target = "id", source = "jpaNotificationEntity.id"),
            @Mapping(target = "notificationType", source = "jpaNotificationEntity.notificationType"),
            @Mapping(target = "artefactId", source = "jpaNotificationEntity.artefactId"),
            @Mapping(target = "message", source = "jpaNotificationEntity.message"),
            @Mapping(target = "createdAt", source = "jpaNotificationEntity.createdAt")})
    Notification jpaToDomain(JpaNotificationEntity jpaNotificationEntity);

    @Mappings({
            @Mapping(target = "id", source = "notification.id"),
            @Mapping(target = "notificationType", source = "notification.notificationType"),
            @Mapping(target = "artefactId", source = "notification.artefactId"),
            @Mapping(target = "message", source = "notification.message")})
    JpaNotificationEntity domainToJpa(Notification notification);
}
