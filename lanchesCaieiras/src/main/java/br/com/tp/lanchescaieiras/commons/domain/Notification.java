package br.com.tp.lanchescaieiras.commons.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"timestamp"})
public class Notification extends ApplicationEvent {
    private Integer id;
    private String notificationType;
    private Integer artefactId;
    private String message;
    private LocalDateTime createdAt;

    public Notification(Object source, Integer id, String notificationType, Integer artefactId, String message) {
        super(source);
        this.id = id;
        this.notificationType = notificationType;
        this.artefactId = artefactId;
        this.message = message;
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

    @JsonIgnore
    public Object getSource() {
        return source;
    }

}
