package br.com.tp.lanchescaieiras.commons.adapters.inbound.listeners;

import br.com.tp.lanchescaieiras.commons.applications.applications.NotificationsServicesImpl;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class NotificationListenerImplTest {

    @Test
    void testHandleNotification() {
        NotificationsServicesImpl service = mock(NotificationsServicesImpl.class);
        NotificationListenerImpl listener = new NotificationListenerImpl(service);
        Notification notification = new Notification(new Object(), 1, "INFO", 10, "msg");

        listener.handleNotification(notification);

        verify(service, times(1)).saveNotification(notification);
    }
}
