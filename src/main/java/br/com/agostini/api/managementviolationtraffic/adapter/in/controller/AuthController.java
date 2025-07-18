package br.com.agostini.api.managementviolationtraffic.adapter.in.controller;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.api.managementviolationtraffic.adapter.in.mapper.LoginInboundMapper;
import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.api.managementviolationtraffic.adapter.out.mapper.TokenResponseRepresentationMapper;
import br.com.agostini.api.managementviolationtraffic.application.port.in.AuthUseCase;
import br.com.agostini.openapi.provider.api.AuthApi;
import br.com.agostini.openapi.provider.representation.LoginRequestRepresentation;
import br.com.agostini.openapi.provider.representation.TokenResponseRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthUseCase authUseCase;
    private final LoginInboundMapper loginInboundMapper;
    private final TokenResponseRepresentationMapper tokenResponseRepresentationMapper;

    @Override
    public ResponseEntity<TokenResponseRepresentation> login(LoginRequestRepresentation loginRequestRepresentation) {
        LoginInbound loginInbound = loginInboundMapper.toLoginInbound(loginRequestRepresentation);
        TokenOutbound tokenOutbound = authUseCase.login(loginInbound);
        return ResponseEntity.ok(tokenResponseRepresentationMapper.toTokenResponseRepresentation(tokenOutbound));
    }
}