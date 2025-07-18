package br.com.agostini.api.managementviolationtraffic.adapter.out.mapper;

import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.openapi.provider.representation.TokenResponseRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenResponseRepresentationMapper {
    TokenResponseRepresentation toTokenResponseRepresentation(TokenOutbound tokenOutbound);
}
