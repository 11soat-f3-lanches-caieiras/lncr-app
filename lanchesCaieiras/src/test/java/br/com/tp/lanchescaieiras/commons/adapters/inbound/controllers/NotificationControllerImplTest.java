package br.com.tp.lanchescaieiras.commons.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.commons.applications.applications.NotificationsServicesImpl;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import br.com.tp.lanchescaieiras.commons.domain.NotificationListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotificationControllerImplTest {

    @Test
    void testGetByNotificationType() {
        NotificationsServicesImpl service = mock(NotificationsServicesImpl.class);
        Notification notification = new Notification(new Object(), 1, "INFO", 10, "msg");
        when(service.findByNotificationType("INFO")).thenReturn(List.of(notification));

        NotificationControllerImpl controller = new NotificationControllerImpl(service);
        ResponseEntity<NotificationListResponse> response = controller.getByNotificationType("INFO");

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getNotifications().size());
        assertEquals("INFO", response.getBody().getNotifications().get(0).getNotificationType());
    }
}
