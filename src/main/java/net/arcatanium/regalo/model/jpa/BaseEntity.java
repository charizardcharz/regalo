/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.model.jpa;

import jakarta.persistence.MappedSuperclass;

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
