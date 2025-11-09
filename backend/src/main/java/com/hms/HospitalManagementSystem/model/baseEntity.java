package com.hms.HospitalManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class baseEntity {

    @Column(unique = true, updatable = false, nullable = false)
    private String sid;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate ;

    @Column(nullable = false)
    private LocalDateTime modifiedDate ;

    @Column(nullable = false)
    private String createdBy = "SYSTEM ADMIN";

    @Column(nullable = false)
    private String modifiedBy = "SYSTEM ADMIN";

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate() {
        this.modifiedDate = LocalDateTime.now();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy() {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy() {
        this.modifiedBy = modifiedBy;
    }

    public String getSid() {
        return sid;
    }

    public void setSid() {
        if (this.sid == null || this.sid.isEmpty()) {
            this.sid = UUID.randomUUID().toString();
        }
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "sid='" + sid + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}
