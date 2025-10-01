package br.com.tp.lncr.app.datasources.postgres.notification;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications",
        schema = "public",
        indexes = {
                @Index(name = "notifications_type_idx", columnList = "notificationType")
        })
public class JpaNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notifications_id_seq")
    @SequenceGenerator(name = "notifications_id_seq", sequenceName = "notifications_id_seq", allocationSize = 1)
    private Integer id;
    private String notificationType;
    private Integer artefactId;
    private String message;
    private LocalDateTime created;

    public JpaNotificationEntity() {
        // Default constructor for JPA
    }

    public JpaNotificationEntity(String notificationType, Integer artefactId, String message) {
        this.notificationType = notificationType;
        this.artefactId = artefactId;
        this.message = message;
        this.created = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Integer getArtefactId() {
        return artefactId;
    }

    public void setArtefactId(Integer artefactId) {
        this.artefactId = artefactId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
    }
}
