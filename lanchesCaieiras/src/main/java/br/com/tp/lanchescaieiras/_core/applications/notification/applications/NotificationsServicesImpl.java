package br.com.tp.lanchescaieiras._core.applications.notification.applications;

import br.com.tp.lanchescaieiras._core.applications.notification.usecases.NotificationUseCases;
import br.com.tp.lanchescaieiras._core.domain.notification.Notification;
import br.com.tp.lanchescaieiras._external.datasources.postgres.notification.JpaNotificationsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsServicesImpl implements NotificationUseCases {

    @Autowired
    private final JpaNotificationsRepositoryImpl notificationRepository;


    public NotificationsServicesImpl(JpaNotificationsRepositoryImpl notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.saveNotification(notification);
    }

    @Override
    public List<Notification> findByNotificationType(String artefactType) {
        List<Notification> notificationList = notificationRepository.findByNotificationType(artefactType).
                stream().toList();
        return notificationList;
    }


}
