package br.com.tp.lanchescaieiras._external.datasources.postgres.notification;

import br.com.tp.lanchescaieiras._core.applications.notification.mappers.NotificationMapper;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationRepository;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;
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
        JpaNotificationEntity jpaNotificationEntity = jpaNotificationsRepository.save(notificationMapper.domainToJpa(notification));
    }

    @Override
    public List<Notification> findByNotificationType(String artefactType) {
        List<JpaNotificationEntity> list = jpaNotificationsRepository.findByNotificationType(artefactType).
                stream().toList();
        return list.stream().map(notificationMapper::jpaToDomain).collect(Collectors.toList());
    }
}
