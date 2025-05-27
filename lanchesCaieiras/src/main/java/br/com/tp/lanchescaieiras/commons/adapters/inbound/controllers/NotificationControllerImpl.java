package br.com.tp.lanchescaieiras.commons.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.commons.applications.applications.NotificationsServicesImpl;
import br.com.tp.lanchescaieiras.commons.domain.NotificationListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationControllerImpl implements NotificationController {

    private final NotificationsServicesImpl notificationsServices;

    public NotificationControllerImpl(NotificationsServicesImpl notificationsServices) {
        this.notificationsServices = notificationsServices;
    }

    @Override
    @GetMapping("/{notificationType}")
    public ResponseEntity<NotificationListResponse> getByNotificationType(@PathVariable(name = "notificationType") String notificationType) {
        NotificationListResponse notificationListResponse = new NotificationListResponse(
                notificationsServices.findByNotificationType(notificationType));
        return ResponseEntity.ok(notificationListResponse);
    }
}
