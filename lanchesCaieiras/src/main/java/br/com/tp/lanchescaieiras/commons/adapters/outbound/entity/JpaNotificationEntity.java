package br.com.tp.lanchescaieiras.commons.adapters.outbound.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class JpaNotificationEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String notificationType;
    private Integer artefactId;
    private String message;
    private LocalDateTime createdAt;

    public JpaNotificationEntity() {
        // Default constructor for JPA
    }

    public JpaNotificationEntity(String notificationType, Integer artefactId, String message) {
        this.notificationType = notificationType;
        this.artefactId = artefactId;
        this.message = message;
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
