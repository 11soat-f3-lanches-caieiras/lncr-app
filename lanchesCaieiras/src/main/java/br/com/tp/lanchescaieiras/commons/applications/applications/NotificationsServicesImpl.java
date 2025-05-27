package br.com.tp.lanchescaieiras.commons.applications.applications;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.repositories.JpaNotificationsRepositoryImpl;
import br.com.tp.lanchescaieiras.commons.applications.usecases.NotificationUseCases;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsServicesImpl implements NotificationUseCases {

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
