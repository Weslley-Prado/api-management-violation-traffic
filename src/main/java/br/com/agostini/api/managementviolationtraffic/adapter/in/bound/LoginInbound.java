package br.com.agostini.api.managementviolationtraffic.adapter.in.bound;

import lombok.Builder;

@Builder
public record LoginInbound(
        String username,
        String password
) {}
