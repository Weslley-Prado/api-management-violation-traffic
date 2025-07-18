package br.com.agostini.api.managementviolationtraffic.application.domain.dto;

import lombok.Builder;

@Builder
public record LoginRequest(
        String username,
        String password
) {
}
