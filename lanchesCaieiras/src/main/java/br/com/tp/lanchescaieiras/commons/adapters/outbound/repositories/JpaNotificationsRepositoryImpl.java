package br.com.tp.lanchescaieiras.commons.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.entity.JpaNotificationEntity;
import br.com.tp.lanchescaieiras.commons.applications.mappers.NotificationMapper;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import br.com.tp.lanchescaieiras.commons.domain.NotificationRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaNotificationsRepositoryImpl implements NotificationRepository {

    private final JpaNotificationsRepository jpaNotificationsRepository;
    private final NotificationMapper notificationMapper;

    public JpaNotificationsRepositoryImpl(@Lazy JpaNotificationsRepository jpaNotificationsRepository,
                                          NotificationMapper notificationMapper) {
        this.jpaNotificationsRepository = jpaNotificationsRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public void saveNotification(Notification notification) {
        jpaNotificationsRepository.save(notificationMapper.domainToJpa(notification));
    }

    @Override
    public List<Notification> findByNotificationType(String artefactType) {
        List<JpaNotificationEntity> list = jpaNotificationsRepository.findByNotificationType(artefactType).
                stream().toList();
        return list.stream().map(notificationMapper::jpaToDomain).collect(Collectors.toList());
    }
}
