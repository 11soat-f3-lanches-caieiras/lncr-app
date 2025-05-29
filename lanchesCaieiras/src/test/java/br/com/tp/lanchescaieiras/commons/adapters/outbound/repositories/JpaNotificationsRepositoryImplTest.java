package br.com.tp.lanchescaieiras.commons.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.entity.JpaNotificationEntity;
import br.com.tp.lanchescaieiras.commons.applications.mappers.NotificationMapper;
import br.com.tp.lanchescaieiras.commons.domain.Notification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JpaNotificationsRepositoryImplTest {

    @Test
    void testSaveNotification() {
        JpaNotificationsRepository repo = mock(JpaNotificationsRepository.class);
        NotificationMapper mapper = mock(NotificationMapper.class);
        Notification notification = new Notification(new Object(), 1, "INFO", 10, "msg");
        JpaNotificationEntity entity = new JpaNotificationEntity("INFO", 10, "msg");
        when(mapper.domainToJpa(notification)).thenReturn(entity);

        JpaNotificationsRepositoryImpl impl = new JpaNotificationsRepositoryImpl(repo, mapper);
        impl.saveNotification(notification);

        verify(repo, times(1)).save(entity);
    }

    @Test
    void testFindByNotificationType() {
        JpaNotificationsRepository repo = mock(JpaNotificationsRepository.class);
        NotificationMapper mapper = mock(NotificationMapper.class);
        JpaNotificationEntity entity = new JpaNotificationEntity("INFO", 10, "msg");
        Notification notification = new Notification(new Object(), 1, "INFO", 10, "msg");
        when(repo.findByNotificationType("INFO")).thenReturn(List.of(entity));
        when(mapper.jpaToDomain(entity)).thenReturn(notification);

        JpaNotificationsRepositoryImpl impl = new JpaNotificationsRepositoryImpl(repo, mapper);
        List<Notification> result = impl.findByNotificationType("INFO");

        assertEquals(1, result.size());
        assertEquals("INFO", result.get(0).getNotificationType());
    }
}
