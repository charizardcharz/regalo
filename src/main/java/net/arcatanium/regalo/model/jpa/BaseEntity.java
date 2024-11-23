package net.arcatanium.regalo.model.jpa;

import jakarta.persistence.Version;
import lombok.Data;

import java.time.Instant;

@Data
public abstract class BaseEntity {

    private Instant insertTimeStamp;

    private Instant lastUpdateTimeStamp;

    @Version
    private Integer version;
}
