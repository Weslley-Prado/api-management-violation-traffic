package br.com.agostini.api.managementviolationtraffic.adapter.out.processor;

import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;
import br.com.agostini.api.managementviolationtraffic.application.port.out.UserAuthenticationPort;

public class JpaUserAuthenticationAdapter implements UserAuthenticationPort {


    @Override
    public TokenResponse authenticate(LoginRequest loginRequest) {
        return null;
    }
}
