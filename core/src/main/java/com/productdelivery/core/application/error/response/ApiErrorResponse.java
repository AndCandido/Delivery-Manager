package com.productdelivery.core.application.error.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiErrorResponse(
    List<String> errors,
    String errorCode,
    int status,
    LocalDateTime timestamp
) {
}
