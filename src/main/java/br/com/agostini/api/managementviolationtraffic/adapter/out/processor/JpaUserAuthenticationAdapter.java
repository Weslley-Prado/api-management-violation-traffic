package br.com.agostini.api.managementviolationtraffic.adapter.out.processor;

import br.com.agostini.api.managementviolationtraffic.application.port.out.UserAuthenticationPort;

public class JpaUserAuthenticationAdapter implements UserAuthenticationPort {
    @Override
    public Boolean authenticate(String username, String password) {
        return null;
    }
}
