package br.com.tp.lanchescaieiras.commons.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.commons.adapters.outbound.entity.JpaNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaNotificationsRepository extends JpaRepository<JpaNotificationEntity, Integer> {

    @Query(value = "SELECT * FROM notifications n WHERE n.notification_type = :notificationType order by created_at desc", nativeQuery = true)
    List<JpaNotificationEntity> findByNotificationType(@Param("notificationType") String notificationType);
}
