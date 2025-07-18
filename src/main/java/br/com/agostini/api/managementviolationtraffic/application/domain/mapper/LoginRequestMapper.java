package br.com.agostini.api.managementviolationtraffic.application.domain.mapper;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginRequestMapper {

    LoginRequest toLoginRequest(LoginInbound loginInbound);
}
