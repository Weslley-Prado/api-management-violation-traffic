package br.com.agostini.api.managementviolationtraffic.application.port.out;

import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;

public interface UserAuthenticationPort {
    TokenResponse authenticate(LoginRequest loginRequest);
}
