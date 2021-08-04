package com.aswebsiteapi.util;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class DefaultEntity {

    @Id
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "udpated_at", nullable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public DefaultEntity(DefaultEntityBuilder<?> builder) {
        this.id = builder.getId();
        this.updatedAt = builder.getUpdatedAt();
        this.createdAt = builder.getCreatedAt();
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

}
