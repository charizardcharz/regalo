package net.arcatanium.regalo.model.jpa;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @CreationTimestamp
    private Instant insertTimeStamp;

    @UpdateTimestamp
    private Instant lastUpdateTimeStamp;
}
