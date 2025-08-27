package br.com.tp.lncr.core.adapters.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.commons.interfaces.notification.NotificationDatabase;
import br.com.tp.lncr.core.domain.notification.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationGatewayImplTest {
    private NotificationDatabase database;
    private NotificationMapper mapper;
    private NotificationGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        database = mock(NotificationDatabase.class);
        mapper = new NotificationMapper();
        gateway = new NotificationGatewayImpl(database, mapper);
    }

    @Test
    void testSaveNotification() {
        Notification notification = new Notification(null,null,null,null,null);
        gateway.saveNotification(notification);
        verify(database, times(1)).save(any(NotificationDTO.class));
    }

    @Test
    void testGetNotificationsByType() {
        NotificationDTO dto = new NotificationDTO();
        when(database.findByNotificationType("type")).thenReturn(Collections.singletonList(dto));
        List<Notification> result = gateway.getNotificationsByType("type");
        assertEquals(1, result.size());
    }

    @Test
    void testGetNotificationTypesList() {
        when(database.findNotificationTypeList()).thenReturn(Collections.singletonList("type"));
        List<String> types = gateway.getNotificationTypesList();
        assertEquals(1, types.size());
        assertEquals("type", types.get(0));
    }
}

