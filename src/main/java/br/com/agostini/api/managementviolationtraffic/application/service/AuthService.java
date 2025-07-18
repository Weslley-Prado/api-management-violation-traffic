package br.com.agostini.api.managementviolationtraffic.application.service;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;
import br.com.agostini.api.managementviolationtraffic.application.domain.mapper.LoginRequestMapper;
import br.com.agostini.api.managementviolationtraffic.application.domain.mapper.TokenOutboundMapper;
import br.com.agostini.api.managementviolationtraffic.application.port.in.AuthUseCase;
import br.com.agostini.api.managementviolationtraffic.application.port.out.UserAuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserAuthenticationPort userAuthenticationPort;
    private final LoginRequestMapper loginRequestMapper;
    private final TokenOutboundMapper tokenOutboundMapper;

    @Override
    public TokenOutbound login(LoginInbound loginInbound) {
        LoginRequest loginRequest = loginRequestMapper.toLoginRequest(loginInbound);
        TokenResponse tokenResponse = userAuthenticationPort.authenticate(loginRequest);
        return tokenOutboundMapper.toTokenResponseRepresentation(tokenResponse);
    }
}