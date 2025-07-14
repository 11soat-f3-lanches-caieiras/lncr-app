package br.com.tp.lanchescaieiras._external.datasources.postgres.notification;

import br.com.tp.lanchescaieiras._core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.notification.NotificationDatabase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaNotificationRepositoryImpl implements NotificationDatabase {

    private final JpaNotificationsRepository jpaNotificationsRepository;
    private final JpaNotificationMapper jpaNotificationMapper;

    public JpaNotificationRepositoryImpl(JpaNotificationsRepository jpaNotificationsRepository,
                                         JpaNotificationMapper jpaNotificationMapper) {
        this.jpaNotificationsRepository = jpaNotificationsRepository;
        this.jpaNotificationMapper = jpaNotificationMapper;
    }

    @Override
    public void save(NotificationDTO notificationDTO) {
        JpaNotificationEntity jpaNotificationEntity = jpaNotificationsRepository.save(jpaNotificationMapper.notificationDtoToJpa(notificationDTO));
    }

    @Override
    public List<NotificationDTO> findByNotificationType(String artefactType) {
        List<JpaNotificationEntity> list = jpaNotificationsRepository.findByNotificationType(artefactType).
                stream().toList();
        return list.stream().map(jpaNotificationMapper::jpaNotificationToDTO).collect(Collectors.toList());
    }
}
