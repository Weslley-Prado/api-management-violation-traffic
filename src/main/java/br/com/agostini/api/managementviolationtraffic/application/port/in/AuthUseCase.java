package br.com.agostini.api.managementviolationtraffic.application.port.in;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;

public interface AuthUseCase {
    TokenOutbound login(LoginInbound request);
}
