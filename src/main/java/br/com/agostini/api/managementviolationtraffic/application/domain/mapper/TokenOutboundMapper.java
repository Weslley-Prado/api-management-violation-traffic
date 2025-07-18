package br.com.agostini.api.managementviolationtraffic.application.domain.mapper;

import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenOutboundMapper {
    TokenOutbound toTokenResponseRepresentation(TokenResponse tokenResponse);
}