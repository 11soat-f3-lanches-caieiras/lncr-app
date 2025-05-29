package br.com.tp.lanchescaieiras.commons.applications.applications;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.repositories.JpaNotificationsRepositoryImpl;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationsServicesImplTest {

    @Test
    void testSaveNotification() {
        JpaNotificationsRepositoryImpl repo = mock(JpaNotificationsRepositoryImpl.class);
        NotificationsServicesImpl service = new NotificationsServicesImpl(repo);
        Notification notification = new Notification(new Object(), 1, "INFO", 10, "msg");

        service.saveNotification(notification);

        verify(repo, times(1)).saveNotification(notification);
    }

    @Test
    void testFindByNotificationType() {
        JpaNotificationsRepositoryImpl repo = mock(JpaNotificationsRepositoryImpl.class);
        Notification notification = new Notification(new Object(), 1, "INFO", 10, "msg");
        when(repo.findByNotificationType("INFO")).thenReturn(List.of(notification));
        NotificationsServicesImpl service = new NotificationsServicesImpl(repo);

        List<Notification> result = service.findByNotificationType("INFO");

        assertEquals(1, result.size());
        assertEquals("INFO", result.get(0).getNotificationType());
    }
}
