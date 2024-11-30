/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class RegaloUtils {
    private RegaloUtils(){}

    public static boolean isValidUUID (String uuid){
        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException ex) {
            log.error("{} is not a valid uuid", uuid);
            return false;
        }
        return true;
    }
}
