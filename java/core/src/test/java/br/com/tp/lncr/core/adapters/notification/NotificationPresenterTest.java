package br.com.tp.lncr.core.adapters.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.domain.notification.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationPresenterTest {
    private NotificationMapper mapper;
    private NotificationPresenter presenter;

    @BeforeEach
    void setUp() {
        mapper = new NotificationMapper();
        presenter = new NotificationPresenter(mapper);
    }

    @Test
    void testGetByTypeReturnsDTOList() {
        Notification notification = new Notification(null, null, null, null, null);
        List<Notification> notifications = Collections.singletonList(notification);
        List<NotificationDTO> dtos = presenter.getByType(notifications);
        assertEquals(1, dtos.size());
        assertNotNull(dtos.get(0));
    }

    @Test
    void testGetByTypeWithEmptyList() {
        List<NotificationDTO> dtos = presenter.getByType(Collections.emptyList());
        assertTrue(dtos.isEmpty());
    }
}

