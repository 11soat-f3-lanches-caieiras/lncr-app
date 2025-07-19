package br.com.tp.lncr.core.adapters.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.domain.notification.Notification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotificationMapperTest {
    private final NotificationMapper mapper = new NotificationMapper();

    @Test
    void testNotificationToDTOAndBack() {
        Notification notification = new Notification(null, null, null, null, null);
        NotificationDTO dto = mapper.notificationToDTO(notification);
        assertNotNull(dto);
        Notification domain = mapper.notificationToDomain(dto);
        assertNotNull(domain);
    }

    @Test
    void testNullNotificationToDTO() {
        assertNull(mapper.notificationToDTO(null));
    }

    @Test
    void testNullDTOToDomain() {
        assertNull(mapper.notificationToDomain(null));
    }
}

