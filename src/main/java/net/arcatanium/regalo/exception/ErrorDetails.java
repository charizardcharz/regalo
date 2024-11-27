package net.arcatanium.regalo.exception;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private Instant timestamp;
    private int errorCode;
    private String message;
}
