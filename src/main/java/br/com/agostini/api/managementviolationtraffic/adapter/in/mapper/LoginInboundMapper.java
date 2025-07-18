package br.com.agostini.api.managementviolationtraffic.adapter.in.mapper;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.openapi.provider.representation.LoginRequestRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginInboundMapper {
    LoginInbound toLoginInbound(LoginRequestRepresentation loginRequestRepresentation);
}
