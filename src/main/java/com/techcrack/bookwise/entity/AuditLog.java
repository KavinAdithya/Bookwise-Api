package com.techcrack.bookwise.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AuditLogs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String oldValue;
    private String newValue;
    private String entityType;
    private Long entityId;
    private LocalDateTime loggedAt;

    @ManyToOne
    private Users user;

    public AuditLog() {
        super();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public Users getLoggerUser() {
        return user;
    }

    public void setLoggerUser(Users user) {
        this.user = user;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditLog auditLog)) return false;
        return Objects.equals(getId(), auditLog.getId()) && Objects.equals(getAction(), auditLog.getAction()) && Objects.equals(getOldValue(), auditLog.getOldValue()) && Objects.equals(getNewValue(), auditLog.getNewValue()) && Objects.equals(getEntityType(), auditLog.getEntityType()) && Objects.equals(getEntityId(), auditLog.getEntityId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAction(), getOldValue(), getNewValue(), getEntityType(), getEntityId());
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "action='" + action + '\'' +
                ", entityId=" + entityId +
                ", entityType='" + entityType + '\'' +
                ", loggedAt=" + loggedAt +
                ", loggerUserId=" + user.getId() +
                ", newValue='" + newValue + '\'' +
                ", oldValue='" + oldValue + '\'' +
                '}';
    }
}