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
