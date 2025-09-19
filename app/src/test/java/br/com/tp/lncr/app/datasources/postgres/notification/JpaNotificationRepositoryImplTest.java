package br.com.tp.lncr.app.datasources.postgres.notification;

import br.com.tp.lncr.core.commons.dtos.notification.NotificationDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaNotificationRepositoryImplTest {

    private final JpaNotificationsRepository jpaNotificationsRepository = mock(JpaNotificationsRepository.class);
    private final JpaNotificationMapper jpaNotificationMapper = mock(JpaNotificationMapper.class);
    private final JpaNotificationRepositoryImpl repository = new JpaNotificationRepositoryImpl(jpaNotificationsRepository, jpaNotificationMapper);

    @Test
    void saveNotificationCallsRepositoryAndMapper() {
        NotificationDTO dto = new NotificationDTO(1, "ORDER", 123, "Test message", LocalDateTime.now());
        JpaNotificationEntity entity = new JpaNotificationEntity("ORDER", 123, "Test message");
        JpaNotificationEntity savedEntity = new JpaNotificationEntity("ORDER", 123, "Test message");
        savedEntity.setId(1);

        when(jpaNotificationMapper.notificationDtoToJpa(dto)).thenReturn(entity);
        when(jpaNotificationsRepository.save(entity)).thenReturn(savedEntity);

        repository.save(dto);

        verify(jpaNotificationMapper).notificationDtoToJpa(dto);
        verify(jpaNotificationsRepository).save(entity);
    }

    @Test
    void saveNotificationWithNullDto() {
        when(jpaNotificationMapper.notificationDtoToJpa(null)).thenReturn(null);
        when(jpaNotificationsRepository.save(null)).thenReturn(null);

        repository.save(null);

        verify(jpaNotificationMapper).notificationDtoToJpa(null);
        verify(jpaNotificationsRepository).save(null);
    }

    @Test
    void findNotificationTypeListReturnsCorrectList() {
        List<String> expectedTypes = Arrays.asList("ORDER", "PAYMENT", "KITCHEN");
        when(jpaNotificationsRepository.findNotificationTypeList()).thenReturn(expectedTypes);

        List<String> result = repository.findNotificationTypeList();

        assertEquals(expectedTypes, result);
        verify(jpaNotificationsRepository).findNotificationTypeList();
    }

    @Test
    void findNotificationTypeListReturnsEmptyList() {
        List<String> emptyList = Collections.emptyList();
        when(jpaNotificationsRepository.findNotificationTypeList()).thenReturn(emptyList);

        List<String> result = repository.findNotificationTypeList();

        assertTrue(result.isEmpty());
        verify(jpaNotificationsRepository).findNotificationTypeList();
    }

    @Test
    void findByNotificationTypeReturnsCorrectDTOs() {
        String notificationType = "ORDER";
        JpaNotificationEntity entity1 = new JpaNotificationEntity("ORDER", 123, "Message 1");
        JpaNotificationEntity entity2 = new JpaNotificationEntity("ORDER", 456, "Message 2");
        List<JpaNotificationEntity> entities = Arrays.asList(entity1, entity2);

        NotificationDTO dto1 = new NotificationDTO(1, "ORDER", 123, "Message 1", LocalDateTime.now());
        NotificationDTO dto2 = new NotificationDTO(2, "ORDER", 456, "Message 2", LocalDateTime.now());

        when(jpaNotificationsRepository.findByNotificationType(notificationType)).thenReturn(entities);
        when(jpaNotificationMapper.jpaNotificationToDTO(entity1)).thenReturn(dto1);
        when(jpaNotificationMapper.jpaNotificationToDTO(entity2)).thenReturn(dto2);

        List<NotificationDTO> result = repository.findByNotificationType(notificationType);

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
        verify(jpaNotificationsRepository).findByNotificationType(notificationType);
        verify(jpaNotificationMapper).jpaNotificationToDTO(entity1);
        verify(jpaNotificationMapper).jpaNotificationToDTO(entity2);
    }

    @Test
    void findByNotificationTypeReturnsEmptyListWhenNoEntities() {
        String notificationType = "NONEXISTENT";
        List<JpaNotificationEntity> emptyList = Collections.emptyList();

        when(jpaNotificationsRepository.findByNotificationType(notificationType)).thenReturn(emptyList);

        List<NotificationDTO> result = repository.findByNotificationType(notificationType);

        assertTrue(result.isEmpty());
        verify(jpaNotificationsRepository).findByNotificationType(notificationType);
        verify(jpaNotificationMapper, never()).jpaNotificationToDTO(any());
    }

    @Test
    void findByNotificationTypeWithNullType() {
        when(jpaNotificationsRepository.findByNotificationType(null)).thenReturn(Collections.emptyList());

        List<NotificationDTO> result = repository.findByNotificationType(null);

        assertTrue(result.isEmpty());
        verify(jpaNotificationsRepository).findByNotificationType(null);
    }

    @Test
    void findByNotificationTypeHandlesMapperReturningNull() {
        String notificationType = "ORDER";
        JpaNotificationEntity entity = new JpaNotificationEntity("ORDER", 123, "Message");
        List<JpaNotificationEntity> entities = List.of(entity);

        when(jpaNotificationsRepository.findByNotificationType(notificationType)).thenReturn(entities);
        when(jpaNotificationMapper.jpaNotificationToDTO(entity)).thenReturn(null);

        List<NotificationDTO> result = repository.findByNotificationType(notificationType);

        assertEquals(1, result.size());
        assertNull(result.get(0));
        verify(jpaNotificationsRepository).findByNotificationType(notificationType);
        verify(jpaNotificationMapper).jpaNotificationToDTO(entity);
    }
}
