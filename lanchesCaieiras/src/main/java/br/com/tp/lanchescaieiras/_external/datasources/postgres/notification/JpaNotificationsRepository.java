package br.com.tp.lanchescaieiras._external.datasources.postgres.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JpaNotificationsRepository extends JpaRepository<JpaNotificationEntity, Integer> {

    @Query(value = "SELECT * FROM notifications n WHERE n.notification_type = :notificationType order by created_at desc", nativeQuery = true)
    List<JpaNotificationEntity> findByNotificationType(@Param("notificationType") String notificationType);

    @Query(value = "SELECT DISTINCT n.notification_type FROM notifications n", nativeQuery = true)
    List<String> findNotificationTypeList();
}
